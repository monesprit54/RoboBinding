package org.robobinding.widget.expandablelistview;

import android.widget.ExpandableListView;
import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.ViewListenersAware;
import org.robobinding.viewattribute.event.EventViewAttribute;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Jihun Lee
 */
public class OnGroupExpandAttribute implements EventViewAttribute<ExpandableListView>, ViewListenersAware<ExpandableListViewListeners> {
    private ExpandableListViewListeners adapterViewListeners;

	@Override
	public void setViewListeners(ExpandableListViewListeners adapterViewListeners) {
		this.adapterViewListeners = adapterViewListeners;
	}

	@Override
	public void bind(ExpandableListView view, final Command command) {
        adapterViewListeners.addOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                GroupExpandEvent groupExpandEvent = new GroupExpandEvent(groupPosition);
                command.invoke(groupExpandEvent);
            }
        });
	}

	@Override
	public Class<GroupCollapseEvent> getEventType() {
		return GroupCollapseEvent.class;
	}
}
