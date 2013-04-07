/**
 * Copyright 2013 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.viewattribute;

import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.Map;

import org.robobinding.attribute.AbstractAttribute;
import org.robobinding.attribute.EnumAttribute;
import org.robobinding.attribute.ResolvedGroupAttributes;
import org.robobinding.attribute.StaticResourceAttribute;
import org.robobinding.attribute.ValueModelAttribute;

import android.view.View;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class ChildViewAttributes<T extends View>
{
	private ResolvedGroupAttributes resolvedGroupAttributes;
	private ViewAttributeInitializer viewAttributeInitializer;
	private InitializedChildViewAttributesFactory initializedChildViewAttributesFactory;
	private Map<String, ViewAttribute> childAttributeMap;
	private boolean failOnFirstBindingError;
	
	public ChildViewAttributes(ResolvedGroupAttributes resolvedGroupAttributes, ViewAttributeInitializer viewAttributeInitializer,
			InitializedChildViewAttributesFactory initializedChildViewAttributesFactory)
	{
		this.resolvedGroupAttributes = resolvedGroupAttributes;
		this.viewAttributeInitializer = viewAttributeInitializer;
		this.initializedChildViewAttributesFactory = initializedChildViewAttributesFactory;
		childAttributeMap = newLinkedHashMap();
		failOnFirstBindingError = false;
	}
	
	public void add(String attributeName, ChildViewAttribute childViewAttribute)
	{
		AbstractAttribute attribute = resolvedGroupAttributes.attributeFor(attributeName);
		ViewAttribute viewAttribute = viewAttributeInitializer.initializeChildViewAttribute(childViewAttribute, attribute);
		childAttributeMap.put(attributeName, viewAttribute);
	}
	
	public void add(String propertyAttribute, PropertyViewAttribute<T> propertyViewAttribute)
	{
		ValueModelAttribute attribute = resolvedGroupAttributes.valueModelAttributeFor(propertyAttribute);
		viewAttributeInitializer.initializePropertyViewAttribute(propertyViewAttribute, attribute);
		childAttributeMap.put(propertyAttribute, propertyViewAttribute);
	}
	
	public void failOnFirstBindingError()
	{
		failOnFirstBindingError = true;
	}
	
	public InitializedChildViewAttributes createInitializedChildViewAttributes()
	{
		return initializedChildViewAttributesFactory.create(childAttributeMap, failOnFirstBindingError);
	}

	public final boolean hasAttribute(String attributeName)
	{
		return resolvedGroupAttributes.hasAttribute(attributeName);
	}

	public final ValueModelAttribute valueModelAttributeFor(String attributeName)
	{
		return resolvedGroupAttributes.valueModelAttributeFor(attributeName);
	}

	public final StaticResourceAttribute staticResourceAttributeFor(String attributeName)
	{
		return resolvedGroupAttributes.staticResourceAttributeFor(attributeName);
	}
	
	public final <E extends Enum<E>> EnumAttribute<E> enumAttributeFor(String attributeName)
	{
		return resolvedGroupAttributes.enumAttributeFor(attributeName);
	}
}
