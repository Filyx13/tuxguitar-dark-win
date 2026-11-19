package app.tuxguitar.ui.swt.appearance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import app.tuxguitar.ui.appearance.UIAppearance;
import app.tuxguitar.ui.appearance.UIColorAppearance;
import app.tuxguitar.ui.resource.UIColorModel;
import app.tuxguitar.ui.swt.widget.SWTControl;

public class SWTAppearance implements UIAppearance {

	private static final String PLATFORM_WIN32 = "win32";

	private Display display;
	private boolean darkMode;
	private Map<UIColorAppearance, UIColorModel> colorMap;

	private Color darkWidgetBg;
	private Color darkWidgetFg;
	private Color darkWidgetLightBg;
	private Color darkWidgetHighlightBg;
	private Color darkInputBg;
	private Color darkInputFg;
	private Color darkSelectionFg;
	private Color darkBorderColor;
	private Color darkDisabledFg;
	private Color darkToolCheckedBg;

	public SWTAppearance(Display display) {
		this.display = display;
		this.darkMode = false;
		this.initDarkColors();
		this.createColorResolvers();
	}

	private boolean isWin32() {
		return PLATFORM_WIN32.equals(SWT.getPlatform());
	}

	private void initDarkColors() {
		if( this.display != null && !this.display.isDisposed() ) {
			this.darkWidgetBg = new Color(this.display, 42, 46, 50);
			this.darkWidgetFg = new Color(this.display, 239, 240, 241);
			this.darkWidgetLightBg = new Color(this.display, 49, 54, 59);
			this.darkWidgetHighlightBg = new Color(this.display, 35, 38, 41);
			this.darkInputBg = new Color(this.display, 30, 33, 36);
			this.darkInputFg = new Color(this.display, 239, 240, 241);
			this.darkSelectionFg = new Color(this.display, 255, 255, 255);
			this.darkBorderColor = new Color(this.display, 68, 74, 80);
			this.darkDisabledFg = new Color(this.display, 128, 134, 140);
			this.darkToolCheckedBg = new Color(this.display, 34, 102, 150);
		}
	}

	@Override
	public boolean isDarkMode() {
		return this.darkMode;
	}

	@Override
	public void setDarkMode(boolean darkMode) {
		boolean changed = (this.darkMode != darkMode);
		this.darkMode = darkMode;
		this.createColorResolvers();

		if( this.isWin32() && this.display != null && !this.display.isDisposed() ) {
			this.configureWin32DisplayTheme();
			if( changed || this.darkMode ) {
				this.applyThemeToAllShells();
			}
		}
	}

	private void configureWin32DisplayTheme() {
		Boolean dark = Boolean.valueOf(this.darkMode);
		this.display.setData("org.eclipse.swt.internal.win32.useDarkModeExplorerTheme", dark);
		this.display.setData("org.eclipse.swt.internal.win32.useShellTitleColoring", dark);
		this.display.setData("org.eclipse.swt.internal.win32.menuBarForegroundColor", this.darkMode ? this.darkWidgetFg : null);
		this.display.setData("org.eclipse.swt.internal.win32.menuBarBackgroundColor", this.darkMode ? this.darkWidgetBg : null);
		this.display.setData("org.eclipse.swt.internal.win32.menuBarBorderColor", this.darkMode ? this.darkBorderColor : null);
		this.display.setData("org.eclipse.swt.internal.win32.all.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Canvas.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.List.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Table.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Text.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Tree.use_WS_BORDER", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Table.headerLineColor", this.darkMode ? this.darkBorderColor : null);
		this.display.setData("org.eclipse.swt.internal.win32.Label.disabledForegroundColor", this.darkMode ? this.darkDisabledFg : null);
		this.display.setData("org.eclipse.swt.internal.win32.Combo.useDarkTheme", dark);
		this.display.setData("org.eclipse.swt.internal.win32.ProgressBar.useColors", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Text.useDarkThemeIcons", dark);
		this.display.setData("org.eclipse.swt.internal.win32.Edge.useDarkPreferedColorScheme", dark);
	}

	public void applyThemeToAllShells() {
		if( this.isWin32() && this.display != null && !this.display.isDisposed() ) {
			Shell[] shells = this.display.getShells();
			if( shells != null ) {
				for(Shell shell : shells) {
					if( shell != null && !shell.isDisposed() ) {
						this.applyControlTheme(shell);
						shell.layout(true, true);
					}
				}
			}
		}
	}

	public void applyControlTheme(Control control) {
		if( !this.isWin32() || control == null || control.isDisposed() ) {
			return;
		}

		Object data = control.getData(SWTControl.class.getName());
		SWTControl<?> swtControl = (data instanceof SWTControl<?> ? (SWTControl<?>) data : null);
		boolean hasCustomBg = (swtControl != null && swtControl.hasCustomBgColor());
		boolean hasCustomFg = (swtControl != null && swtControl.hasCustomFgColor());

		if( control instanceof Shell ) {
			Shell shell = (Shell) control;
			shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
			if( !hasCustomBg ) {
				shell.setBackground(this.darkMode ? this.darkWidgetBg : null);
			}
			if( !hasCustomFg ) {
				shell.setForeground(this.darkMode ? this.darkWidgetFg : null);
			}
			this.applyWin32ShellTitleBar(shell, this.darkMode);
			this.applyWin32MenuBarTheme(shell, this.darkMode);
		}
		else if( control instanceof CTabFolder ) {
			CTabFolder tabFolder = (CTabFolder) control;
			tabFolder.setBackground(this.darkMode ? this.darkWidgetHighlightBg : null);
			tabFolder.setForeground(this.darkMode ? this.darkWidgetFg : null);
			tabFolder.setSelectionBackground(this.darkMode ? this.darkWidgetLightBg : null);
			tabFolder.setSelectionForeground(this.darkMode ? this.darkSelectionFg : null);
		}
		else if( control instanceof Table ) {
			Table table = (Table) control;
			if( !hasCustomBg ) {
				table.setBackground(this.darkMode ? this.darkInputBg : null);
			}
			if( !hasCustomFg ) {
				table.setForeground(this.darkMode ? this.darkInputFg : null);
			}
			table.setHeaderBackground(this.darkMode ? this.darkWidgetBg : null);
			table.setHeaderForeground(this.darkMode ? this.darkWidgetFg : null);
			this.applyWin32WindowTheme(table, this.darkMode ? "DarkMode_Explorer" : "Explorer");
		}
		else if( control instanceof Text || control instanceof org.eclipse.swt.widgets.List || control instanceof Spinner ) {
			if( !hasCustomBg ) {
				control.setBackground(this.darkMode ? this.darkInputBg : null);
			}
			if( !hasCustomFg ) {
				control.setForeground(this.darkMode ? this.darkInputFg : null);
			}
			this.applyWin32WindowTheme(control, this.darkMode ? "DarkMode_Explorer" : "Explorer");
		}
		else if( control instanceof Combo ) {
			if( !hasCustomBg ) {
				control.setBackground(this.darkMode ? this.darkWidgetLightBg : null);
			}
			if( !hasCustomFg ) {
				control.setForeground(this.darkMode ? this.darkInputFg : null);
			}
			this.applyWin32WindowTheme(control, this.darkMode ? "DarkMode_CFD" : "CFD");
		}
		else if( control instanceof Button ) {
			Button button = (Button) control;
			int style = button.getStyle();
			if( (style & (SWT.CHECK | SWT.RADIO)) != 0 ) {
				if( !hasCustomBg ) {
					button.setBackground(this.darkMode ? this.darkWidgetBg : null);
				}
				if( !hasCustomFg ) {
					button.setForeground(this.darkMode ? this.darkWidgetFg : null);
				}
			} else {
				if( !hasCustomBg ) {
					button.setBackground(this.darkMode ? this.darkWidgetLightBg : null);
				}
				if( !hasCustomFg ) {
					button.setForeground(this.darkMode ? this.darkWidgetFg : null);
				}
			}
			this.applyWin32WindowTheme(button, this.darkMode ? "DarkMode_Explorer" : "Explorer");
		}
		else if( control instanceof ToolBar || control instanceof Group ) {
			if( control instanceof Composite ) {
				((Composite) control).setBackgroundMode(SWT.INHERIT_DEFAULT);
			}
			if( control instanceof ToolBar ) {
				this.applyWin32WindowTheme(control, this.darkMode ? "DarkMode_Explorer" : "Explorer");
				org.eclipse.swt.widgets.ToolItem[] items = ((ToolBar) control).getItems();
				if( items != null ) {
					for(org.eclipse.swt.widgets.ToolItem item : items) {
						Object itemData = item.getData(app.tuxguitar.ui.swt.toolbar.SWTToolCheckableItem.class.getName());
						if( itemData instanceof app.tuxguitar.ui.swt.toolbar.SWTToolCheckableItem ) {
							((app.tuxguitar.ui.swt.toolbar.SWTToolCheckableItem) itemData).applyCheckState();
						}
					}
				}
			}
			if( !hasCustomBg ) {
				control.setBackground(this.darkMode ? this.darkWidgetBg : null);
			}
			if( !hasCustomFg ) {
				control.setForeground(this.darkMode ? this.darkWidgetFg : null);
			}
		}
		else {
			if( control instanceof Composite ) {
				((Composite) control).setBackgroundMode(SWT.INHERIT_DEFAULT);
				this.applyWin32WindowTheme(control, this.darkMode ? "DarkMode_Explorer" : "Explorer");
			}
			if( !hasCustomBg ) {
				control.setBackground(this.darkMode ? this.darkWidgetBg : null);
			}
			if( !hasCustomFg ) {
				control.setForeground(this.darkMode ? this.darkWidgetFg : null);
			}
		}

		if( control instanceof Composite ) {
			Control[] children = ((Composite) control).getChildren();
			if( children != null ) {
				for(Control child : children) {
					this.applyControlTheme(child);
				}
			}
		}

		control.redraw();
	}

	private void applyWin32ShellTitleBar(Shell shell, boolean dark) {
		try {
			Field handleField = shell.getClass().getField("handle");
			long hwnd = handleField.getLong(shell);
			if( hwnd != 0 ) {
				Class<?> osClass = Class.forName("org.eclipse.swt.internal.win32.OS");
				Method dwmSetAttr = osClass.getMethod("DwmSetWindowAttribute", long.class, int.class, int[].class, int.class);
				int[] value = new int[] { dark ? 1 : 0 };
				dwmSetAttr.invoke(null, hwnd, 20, value, 4);

				Method drawMenuBar = osClass.getMethod("DrawMenuBar", long.class);
				drawMenuBar.invoke(null, hwnd);

				Method setWindowPos = osClass.getMethod("SetWindowPos", long.class, long.class, int.class, int.class, int.class, int.class, int.class);
				// SWP_NOMOVE (0x0002) | SWP_NOSIZE (0x0001) | SWP_NOZORDER (0x0004) | SWP_NOACTIVATE (0x0010) | SWP_FRAMECHANGED (0x0020) = 0x0037
				setWindowPos.invoke(null, hwnd, 0L, 0, 0, 0, 0, 0x0037);
			}
		} catch (Throwable ignored) {
		}
	}

	public void applyWin32MenuBarTheme(Shell shell, boolean dark) {
		try {
			if( shell == null || shell.isDisposed() ) {
				return;
			}
			org.eclipse.swt.widgets.Menu menuBar = shell.getMenuBar();
			if( menuBar != null && !menuBar.isDisposed() ) {
				Class<?> menuClass = menuBar.getClass();
				Field bgField = menuClass.getDeclaredField("background");
				Field fgField = menuClass.getDeclaredField("foreground");
				bgField.setAccessible(true);
				fgField.setAccessible(true);

				int bgPixel = -1;
				int fgPixel = -1;
				if( dark ) {
					Field colorHandleField = Color.class.getDeclaredField("handle");
					colorHandleField.setAccessible(true);
					bgPixel = colorHandleField.getInt(this.darkWidgetBg);
					fgPixel = colorHandleField.getInt(this.darkWidgetFg);
				}
				bgField.setInt(menuBar, bgPixel);
				fgField.setInt(menuBar, fgPixel);

				Method updateBg = menuClass.getDeclaredMethod("updateBackground");
				Method updateFg = menuClass.getDeclaredMethod("updateForeground");
				updateBg.setAccessible(true);
				updateFg.setAccessible(true);
				updateBg.invoke(menuBar);
				updateFg.invoke(menuBar);
			}
		} catch (Throwable ignored) {
		}
	}

	private void applyWin32WindowTheme(Control control, String themeName) {
		try {
			Field handleField = control.getClass().getField("handle");
			long hwnd = handleField.getLong(control);
			if( hwnd != 0 ) {
				Class<?> osClass = Class.forName("org.eclipse.swt.internal.win32.OS");
				Method setWindowTheme = osClass.getMethod("SetWindowTheme", long.class, char[].class, char[].class);
				char[] chars = new char[themeName.length() + 1];
				themeName.getChars(0, themeName.length(), chars, 0);
				if( themeName.isEmpty() ) {
					setWindowTheme.invoke(null, hwnd, new char[] { 0 }, new char[] { 0 });
				} else {
					setWindowTheme.invoke(null, hwnd, chars, null);
				}
			}
		} catch (Throwable ignored) {
		}
	}

	public void applyToolItemTheme(org.eclipse.swt.widgets.ToolItem item, boolean checked) {
		if( !this.isWin32() || item == null || item.isDisposed() ) {
			return;
		}
		item.setBackground(this.darkMode && checked ? this.darkToolCheckedBg : null);
		item.setForeground(this.darkMode ? this.darkWidgetFg : null);
	}

	public void createColorResolvers() {
		this.colorMap = new HashMap<UIColorAppearance, UIColorModel>();
		if( this.isWin32() && this.darkMode ) {
			this.colorMap.put(UIColorAppearance.WidgetBackground, new UIColorModel(42, 46, 50));
			this.colorMap.put(UIColorAppearance.WidgetForeground, new UIColorModel(239, 240, 241));
			this.colorMap.put(UIColorAppearance.WidgetLightBackground, new UIColorModel(49, 54, 59));
			this.colorMap.put(UIColorAppearance.WidgetLightForeground, new UIColorModel(239, 240, 241));
			this.colorMap.put(UIColorAppearance.WidgetHighlightBackground, new UIColorModel(35, 38, 41));
			this.colorMap.put(UIColorAppearance.WidgetHighlightForeground, new UIColorModel(239, 240, 241));
			this.colorMap.put(UIColorAppearance.WidgetSelectedBackground, new UIColorModel(68, 74, 80));
			this.colorMap.put(UIColorAppearance.WidgetSelectedForeground, new UIColorModel(255, 255, 255));
			this.colorMap.put(UIColorAppearance.InputBackground, new UIColorModel(30, 33, 36));
			this.colorMap.put(UIColorAppearance.InputForeground, new UIColorModel(239, 240, 241));
			this.colorMap.put(UIColorAppearance.InputSelectedBackground, new UIColorModel(41, 128, 185));
			this.colorMap.put(UIColorAppearance.InputSelectedForeground, new UIColorModel(255, 255, 255));
		} else {
			this.colorMap.put(UIColorAppearance.WidgetBackground, this.createColorModel(SWT.COLOR_WIDGET_BACKGROUND));
			this.colorMap.put(UIColorAppearance.WidgetForeground, this.createColorModel(SWT.COLOR_WIDGET_FOREGROUND));
			this.colorMap.put(UIColorAppearance.WidgetLightBackground, this.createColorModel(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			this.colorMap.put(UIColorAppearance.WidgetLightForeground, this.createColorModel(SWT.COLOR_WIDGET_FOREGROUND));
			this.colorMap.put(UIColorAppearance.WidgetHighlightBackground, this.createColorModel(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_WIDGET_NORMAL_SHADOW));
			this.colorMap.put(UIColorAppearance.WidgetHighlightForeground, this.createColorModel(SWT.COLOR_WIDGET_FOREGROUND));
			this.colorMap.put(UIColorAppearance.WidgetSelectedBackground, this.createColorModel(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_WIDGET_NORMAL_SHADOW));
			this.colorMap.put(UIColorAppearance.WidgetSelectedForeground, this.createColorModel(SWT.COLOR_LIST_SELECTION_TEXT));
			this.colorMap.put(UIColorAppearance.InputBackground, this.createColorModel(SWT.COLOR_LIST_BACKGROUND));
			this.colorMap.put(UIColorAppearance.InputForeground, this.createColorModel(SWT.COLOR_LIST_FOREGROUND));
			this.colorMap.put(UIColorAppearance.InputSelectedBackground, this.createColorModel(SWT.COLOR_LIST_SELECTION));
			this.colorMap.put(UIColorAppearance.InputSelectedForeground, this.createColorModel(SWT.COLOR_LIST_SELECTION_TEXT));
		}
	}

	public UIColorModel createColorModel(int style) {
		Color color = this.display.getSystemColor(style);

		return new UIColorModel(color.getRed(), color.getGreen(), color.getBlue());
	}

	public UIColorModel createColorModel(int style1, int style2) {
		Color c1 = this.display.getSystemColor(style1);
		Color c2 = this.display.getSystemColor(style2);

		return new UIColorModel(((c1.getRed() + c2.getRed()) / 2), ((c1.getGreen() + c2.getGreen()) / 2), ((c1.getBlue() + c2.getBlue()) / 2));
	}

	@Override
	public UIColorModel getColorModel(UIColorAppearance colorAppearance) {
		if( this.colorMap.containsKey(colorAppearance)) {
			return this.colorMap.get(colorAppearance);
		}
		return new UIColorModel(0x00, 0x00, 0x00);
	}
}
