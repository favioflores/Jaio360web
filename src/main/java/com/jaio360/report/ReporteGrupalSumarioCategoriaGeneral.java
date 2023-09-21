package com.jaio360.report;

import com.jaio360.dao.ResultadoDAO;
import com.jaio360.domain.DatosReporte;
import com.jaio360.model.ModeloGeneral;
import com.jaio360.model.ModeloNormal;
import com.jaio360.utils.Constantes;
import com.jaio360.utils.Utilitarios;
import com.jaio360.view.BaseView;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.RectangleInsets;

public class ReporteGrupalSumarioCategoriaGeneral extends BaseView implements Serializable {

    private static final Log log = LogFactory.getLog(ReporteGrupalSumarioCategoriaGeneral.class);

    ResultadoDAO resultadoDAO = new ResultadoDAO();
    DatosReporte objDatosReporte;

    public String build(DatosReporte objDatosReporte, Map map, String strNameFile) throws IOException {

        this.objDatosReporte = objDatosReporte;

        String strNombreReporte = strNameFile + Constantes.STR_EXTENSION_PDF;
        JasperPdfExporterBuilder pdfExporter = export.pdfExporter(Utilitarios.getPathTempPreliminar() + File.separator + strNombreReporte)
                .setEncrypted(Boolean.FALSE);

        InputStream medida = new FileInputStream(map.get(Constantes.INT_PARAM_GRAF_MEDIDA) + Constantes.STR_EXTENSION_PNG);

        try {

            report().setTemplate(ModeloGeneral.reportTemplate)
                    .setSummaryWithPageHeaderAndFooter(Boolean.TRUE)
                    .pageHeader(generaCabecera(map, medida))
                    .summary(generaContenido())
                    .pageFooter(ModeloGeneral.generaPie(map))
                    .toPdf(pdfExporter);

        } catch (DRException ex) {
            log.error(ex);
        } finally {
            medida.close();
        }

        return strNombreReporte;
    }

    private MultiPageListBuilder generaContenido() {

        List lstDatos = resultadoDAO.listaGrupalSumarioCategoriaGeneral(objDatosReporte);

        TextColumnBuilder<String> evaluacion = col.column("Evaluacion", "evaluacion", type.stringType());
        TextColumnBuilder<String> relacion = col.column("Relacion", "relacion", type.stringType());
        TextColumnBuilder<Double> cantidad = col.column("Cantidad", "cantidad", type.doubleType());
        Map<String, Color> seriesColors;

        MultiPageListBuilder multiPageList = cmp.multiPageList();

        if (!lstDatos.isEmpty()) {

            Iterator itLstDatos = lstDatos.iterator();

            String keyTit = "";
            boolean primeraVez = true;
            int contCat = 0;
            int contador = 0;
            BigDecimal bdProm;

            while (itLstDatos.hasNext()) {

                seriesColors = new HashMap();
                seriesColors.put(msg("prom"), Utilitarios.convertColorHexToRgb("#" + Utilitarios.generaColorHtmlPreferencial(contador)));
                contador++;

                Object obj[] = (Object[]) itLstDatos.next();

                if (!keyTit.equals(obj[2].toString())) {
                    if (!primeraVez) {
                        multiPageList.add(cmp.verticalGap(10));
                    }
                    multiPageList.add(cmp.horizontalList(
                            cmp.text(obj[1].toString()).setStyle(ModeloGeneral.styleTextoRegular)
                    )
                    );
                    keyTit = obj[2].toString();
                    primeraVez = false;
                    contCat++;
                }
                bdProm = new BigDecimal("0");
                if (Utilitarios.noEsNuloOVacio(obj[4])) {
                    bdProm = new BigDecimal(obj[4].toString()).setScale(2, RoundingMode.FLOOR);
                }

                String strDesc;
                if (Utilitarios.noEsNuloOVacio(obj[3])) {
                    strDesc = obj[3].toString();
                } else {
                    strDesc = msg("empty.desc");
                }

                multiPageList.add(cmp.horizontalList(
                        cht.barChart().setCategory(evaluacion)
                                .seriesColorsByName(seriesColors)
                                .series(cht.serie(cantidad).setSeries(relacion))
                                .setDataSource(createDataSourceBar(bdProm))
                                .setOrientation(Orientation.HORIZONTAL)
                                .setLegendPosition(Position.RIGHT)
                                .setShowLegend(Boolean.FALSE)
                                .setShowLabels(Boolean.FALSE)
                                .setShowValues(Boolean.FALSE)
                                .setShowTickLabels(Boolean.FALSE)
                                .setHeight(10)/* es util, en relacion de 10 por 1 categoria*/
                                .setWidth(115)
                                .setShowTickMarks(Boolean.FALSE)
                                .addCustomizer(new ReporteGrupalSumarioCategoriaGeneral.ChartCustomizerBar()),
                        cmp.horizontalGap(60),
                        cmp.horizontalList(
                                cmp.text(Utilitarios.truncateTheDecimal(bdProm, 2)).setStyle(ModeloGeneral.styleContenidoDatos)
                        ).setWidth(35),
                        cmp.horizontalList(
                                cmp.text(strDesc).setStyle(ModeloGeneral.styleContenidoDatos)
                        ).setWidth(87)
                )
                );
            }

        } else {
            multiPageList.add(cmp.text(msg("empty.desc")));
        }

        multiPageList.newPage();

        return multiPageList;

    }

    private JRDataSource createDataSourceBar(BigDecimal bdDato) {
        DRDataSource dataSource = new DRDataSource("evaluacion", "relacion", "cantidad");
        dataSource.add("eva", msg("prom"), bdDato.doubleValue());
        return dataSource;
    }

    private ComponentBuilder<?, ?> generaCabecera(Map map, InputStream medida) throws FileNotFoundException {

        return cmp.verticalList(
                cmp.verticalGap(5),
                cmp.horizontalList(
                        cmp.text(objDatosReporte.getStrDescripcion().toUpperCase()).setStyle(ModeloGeneral.styleTituloReporte)
                ),
                cmp.verticalGap(10),
                cmp.horizontalList(
                        cmp.image(medida).setFixedDimension(225, 20),
                        cmp.horizontalGap(50),
                        cmp.text(msg("prom").toUpperCase()).setStyle(ModeloGeneral.styleHeaderColumnas).setWidth(80),
                        cmp.text(msg("evaluated").toUpperCase()).setStyle(ModeloGeneral.styleHeaderColumnas).setWidth(220)
                ),
                cmp.verticalGap(5)
        );
    }

    public class ChartCustomizerBar implements DRIChartCustomizer, Serializable {

        @Override
        public void customize(JFreeChart chart, ReportParameters reportParameters) {

            BarRenderer barRenderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
            //barRenderer.setBaseSeriesVisible(false);//QUITA LAS BARRAS
            //barRenderer.setBaseSeriesVisibleInLegend(false);
            //barRenderer.setDrawBarOutline(false);
            barRenderer.setShadowVisible(false);
            barRenderer.setBarPainter(new CustomBarPainter());
            barRenderer.setItemMargin(0);//QUITA ESPACIOS ENTRE BARRA Y BARRA

            CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
            domainAxis.setUpperMargin(0);
            domainAxis.setLowerMargin(0);
            domainAxis.setCategoryMargin(0);
            domainAxis.setAxisLineVisible(false);//este es util

            Plot plot = chart.getPlot();
            //plot.setOutlineVisible(false);
            plot.setInsets(new RectangleInsets(0, 0, 0, 0));//este sera util

            CategoryPlot categoryPlot = chart.getCategoryPlot();
            categoryPlot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
            categoryPlot.setDomainGridlinesVisible(false);
            categoryPlot.setRangeGridlinesVisible(true);// Muestra las lineas punteadas entre las barras
            categoryPlot.setRangeGridlinePaint(ModeloGeneral.colorJAIOYellow);

            //categoryPlot.setBackgroundPaint(Color.white);
            categoryPlot.setOutlineVisible(false);

            ValueAxis valueAsix = categoryPlot.getRangeAxis();
            valueAsix.setAxisLineVisible(false);//este es muy util
            valueAsix.setRange(0, objDatosReporte.getIntMaxRango());
            valueAsix.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//este es muy util

        }
    }

}
