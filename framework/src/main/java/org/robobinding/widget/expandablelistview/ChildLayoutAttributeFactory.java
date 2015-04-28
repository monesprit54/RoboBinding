package org.robobinding.widget.expandablelistview;

import android.widget.ExpandableListView;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Jihun Lee
 */
public class ChildLayoutAttributeFactory extends ExpandableRowLayoutAttributeFactory {
	public ChildLayoutAttributeFactory(
            ExpandableListView expandableListView,
            DataSetExpandableListAdapterBuilder adapterBuilder) {
		super(expandableListView, adapterBuilder);
	}

    @Override
    protected ExpandableRowLayoutUpdater createChildLayoutUpdater(
            DataSetExpandableListAdapterBuilder adapterBuilder) {
        return new ChildLayoutUpdater(adapterBuilder);
    }
}