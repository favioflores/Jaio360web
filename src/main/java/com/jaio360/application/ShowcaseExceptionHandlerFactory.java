/*
 * Copyright 2009-2012 Prime Teknoloji.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jaio360.application;

import java.io.Serializable;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ShowcaseExceptionHandlerFactory extends ExceptionHandlerFactory implements Serializable{

    private ExceptionHandlerFactory base;
    
    private ShowcaseExceptionHandler cached;
    
    public ShowcaseExceptionHandlerFactory(ExceptionHandlerFactory base) {
        this.base = base;
    }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        if(cached == null) {
            cached = new ShowcaseExceptionHandler(base.getExceptionHandler());
        }
        
        return cached;
    }
}
