/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cameltooling.idea.completion.contributor;

import com.github.cameltooling.idea.completion.extension.BeanReferenceCompletionExtension;
import com.github.cameltooling.idea.completion.extension.BlueprintPropertyNameCompletionExtension;
import com.github.cameltooling.idea.completion.extension.CamelEndpointNameCompletionExtension;
import com.github.cameltooling.idea.completion.extension.CamelEndpointSmartCompletionExtension;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiFile;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * Plugin to hook into the IDEA XML language, to setup Camel smart completion for editing XML source code.
 */
public class CamelYamlReferenceContributor extends CamelContributor {

    public CamelYamlReferenceContributor() {
        addCompletionExtension(new BeanReferenceCompletionExtension());
        addCompletionExtension(new BlueprintPropertyNameCompletionExtension());
        addCompletionExtension(new CamelEndpointNameCompletionExtension());
        addCompletionExtension(new CamelEndpointSmartCompletionExtension(true));
        extend(CompletionType.BASIC,
                psiElement().and(psiElement().inside(PsiFile.class).inFile(matchFileType("yaml", "yml"))),
                new EndpointCompletion(getCamelCompletionExtensions())
        );
    }

}
