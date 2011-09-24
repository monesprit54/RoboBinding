/**
 * Copyright 2011 Cheng Wei, Robert Taylor
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
package robobinding.binding;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

/**
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 *
 */
class BindingFactory implements Factory
{
	private final LayoutInflater layoutInflater;
	private final HashMap<View, BindingMap> viewBindingsMap = new HashMap<View, BindingMap>();

	BindingFactory(LayoutInflater layoutInflater)
	{
		this.layoutInflater = layoutInflater;
	}

	public View onCreateView(String name, Context context, AttributeSet attrs)
	{
		try
		{
			View view = layoutInflater.createView(name, null, attrs);
			viewBindingsMap.put(view, BindingMap.createFrom(attrs));
			return view;
		} 
		catch (InflateException e)
		{
			throw new RuntimeException(e);
		} 
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	Map<View, BindingMap> getViewBindingsMap()
	{
		return viewBindingsMap;
	}
}
