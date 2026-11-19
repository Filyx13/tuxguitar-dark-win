package app.tuxguitar.ui.swt.toolbar;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolItem;
import app.tuxguitar.ui.swt.SWTEnvironment;
import app.tuxguitar.ui.swt.appearance.SWTAppearance;
import app.tuxguitar.ui.toolbar.UIToolCheckableItem;

public class SWTToolCheckableItem extends SWTToolActionItem implements UIToolCheckableItem {

	private boolean checked;

	public SWTToolCheckableItem(ToolItem item, SWTToolBar parent) {
		super(item, parent);
		this.checked = false;
		this.getControl().setData(SWTToolCheckableItem.class.getName(), this);
		this.getControl().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SWTToolCheckableItem.this.applyCheckState();
			}
		});
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		this.applyCheckState();
	}

	public void applyCheckState() {
		if( !this.getControl().isDisposed() ) {
			SWTAppearance appearance = SWTEnvironment.getInstance().getAppearance();
			if( appearance != null && appearance.isDarkMode() ) {
				this.getControl().setSelection(false);
				appearance.applyToolItemTheme(this.getControl(), this.checked);
			} else {
				this.getControl().setBackground(null);
				this.getControl().setSelection(this.checked);
			}
		}
	}
}
