package app.tuxguitar.app.view.component.tab;

import app.tuxguitar.app.system.config.TGConfigKeys;
import app.tuxguitar.app.system.config.TGConfigManager;
import app.tuxguitar.graphics.control.TGLayoutStyles;
import app.tuxguitar.ui.resource.UIColorModel;

public class TablatureStyles extends TGLayoutStyles {

	private static final String DEFAULT_COLOR_FOREGROUND = "0,0,0";
	private static final String DEFAULT_COLOR_BACKGROUND = "255,255,255";
	private static final String DEFAULT_COLOR_BACKGROUND_PLAYING = "255,242,242";
	private static final String DEFAULT_COLOR_LINE = "214,214,214";
	private static final String DEFAULT_COLOR_SCORE_NOTE = "64,64,64";
	private static final String DEFAULT_COLOR_TAB_NOTE = "64,64,64";
	private static final String DEFAULT_COLOR_PLAY_NOTE = "255,0,0";
	private static final String DEFAULT_COLOR_MEASURE_NUMBER = "128,0,0";

	public TablatureStyles(TGConfigManager config) {
		this.setBufferEnabled(true);
		this.setStringSpacing(config.getIntegerValue(TGConfigKeys.STYLE_STRING_SPACING));
		this.setScoreLineSpacing(config.getIntegerValue(TGConfigKeys.STYLE_SCORE_LINE_SPACING));
		this.setFirstMeasureSpacing(config.getIntegerValue(TGConfigKeys.STYLE_FIRST_MEASURE_SPACING));
		this.setMinBufferSeparator(config.getIntegerValue(TGConfigKeys.STYLE_MIN_BUFFER_SEPARATOR));
		this.setMinTopSpacing(config.getIntegerValue(TGConfigKeys.STYLE_MIN_TOP_SPACING));
		this.setMinScoreTabSpacing(config.getIntegerValue(TGConfigKeys.STYLE_MIN_SCORE_TAB_SPACING));
		this.setFirstTrackSpacing(config.getIntegerValue(TGConfigKeys.STYLE_FIRST_TRACK_SPACING));
		this.setTrackSpacing(config.getIntegerValue(TGConfigKeys.STYLE_TRACK_SPACING));
		this.setFirstNoteSpacing(config.getIntegerValue(TGConfigKeys.STYLE_FIRST_NOTE_SPACING));
		this.setMeasureLeftSpacing(config.getIntegerValue(TGConfigKeys.STYLE_MEASURE_LEFT_SPACING));
		this.setMeasureRightSpacing(config.getIntegerValue(TGConfigKeys.STYLE_MEASURE_RIGHT_SPACING));
		this.setClefSpacing(config.getIntegerValue(TGConfigKeys.STYLE_CLEF_SPACING));
		this.setKeySignatureSpacing(config.getIntegerValue(TGConfigKeys.STYLE_KEY_SIGNATURE_SPACING));
		this.setTimeSignatureSpacing(config.getIntegerValue(TGConfigKeys.STYLE_TIME_SIGNATURE_SPACING));
		this.setChordFretIndexSpacing(config.getIntegerValue(TGConfigKeys.STYLE_CHORD_FRET_INDEX_SPACING));
		this.setChordStringSpacing(config.getIntegerValue(TGConfigKeys.STYLE_CHORD_STRING_SPACING));
		this.setChordFretSpacing(config.getIntegerValue(TGConfigKeys.STYLE_CHORD_FRET_SPACING));
		this.setChordNoteSize(config.getIntegerValue(TGConfigKeys.STYLE_CHORD_NOTE_SIZE));
		this.setChordLineWidth(config.getIntegerValue(TGConfigKeys.STYLE_CHORD_LINE_WIDTH));
		this.setRepeatEndingSpacing(config.getIntegerValue(TGConfigKeys.STYLE_REPEAT_ENDING_SPACING));
		this.setTextSpacing(config.getIntegerValue(TGConfigKeys.STYLE_TEXT_SPACING));
		this.setMarkerSpacing(config.getIntegerValue(TGConfigKeys.STYLE_MARKER_SPACING));
		this.setLoopMarkerSpacing(config.getIntegerValue(TGConfigKeys.STYLE_LOOP_MARKER_SPACING));
		this.setDivisionTypeSpacing(config.getIntegerValue(TGConfigKeys.STYLE_DIVISION_TYPE_SPACING));
		this.setPickStrokeSpacing(config.getIntegerValue(TGConfigKeys.STYLE_PICK_STROKE_SPACING));
		this.setBendSpacing(config.getIntegerValue(TGConfigKeys.STYLE_BEND_SPACING));
		this.setEffectSpacing(config.getIntegerValue(TGConfigKeys.STYLE_EFFECT_SPACING));
		this.setLineWidths(config.getFloatArrayValue(TGConfigKeys.STYLE_LINE_WIDTHS));
		this.setDurationWidths(config.getFloatArrayValue(TGConfigKeys.STYLE_DURATION_WIDTHS));

		this.setDefaultFont(config.getFontModelConfigValue(TGConfigKeys.FONT_DEFAULT));
		this.setNoteFont(config.getFontModelConfigValue(TGConfigKeys.FONT_NOTE));
		this.setLyricFont(config.getFontModelConfigValue(TGConfigKeys.FONT_LYRIC));
		this.setTextFont(config.getFontModelConfigValue(TGConfigKeys.FONT_TEXT));
		this.setMarkerFont(config.getFontModelConfigValue(TGConfigKeys.FONT_MARKER));
		this.setGraceFont(config.getFontModelConfigValue(TGConfigKeys.FONT_GRACE));
		this.setChordFont(config.getFontModelConfigValue(TGConfigKeys.FONT_CHORD));
		this.setChordFretFont(config.getFontModelConfigValue(TGConfigKeys.FONT_CHORD_FRET));

		boolean darkScore = Boolean.TRUE.equals(config.getBooleanValue(TGConfigKeys.STYLE_DARK_SCORE));
		if (darkScore) {
			this.setForegroundColor(isDefaultColor(config, TGConfigKeys.COLOR_FOREGROUND, DEFAULT_COLOR_FOREGROUND)
					? new UIColorModel(230, 232, 235)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_FOREGROUND));
			this.setBackgroundColor(isDefaultColor(config, TGConfigKeys.COLOR_BACKGROUND, DEFAULT_COLOR_BACKGROUND)
					? new UIColorModel(30, 33, 36)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_BACKGROUND));
			this.setBackgroundColorPlaying(isDefaultColor(config, TGConfigKeys.COLOR_BACKGROUND_PLAYING, DEFAULT_COLOR_BACKGROUND_PLAYING)
					? new UIColorModel(58, 38, 40)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_BACKGROUND_PLAYING));
			this.setLineColor(isDefaultColor(config, TGConfigKeys.COLOR_LINE, DEFAULT_COLOR_LINE)
					? new UIColorModel(85, 92, 100)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_LINE));
			this.setLineColorInvalid(config.getColorModelConfigValue(TGConfigKeys.COLOR_LINE_INVALID));
			this.setScoreNoteColor(isDefaultColor(config, TGConfigKeys.COLOR_SCORE_NOTE, DEFAULT_COLOR_SCORE_NOTE)
					? new UIColorModel(225, 228, 232)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_SCORE_NOTE));
			this.setTabNoteColor(isDefaultColor(config, TGConfigKeys.COLOR_TAB_NOTE, DEFAULT_COLOR_TAB_NOTE)
					? new UIColorModel(225, 228, 232)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_TAB_NOTE));
			this.setPlayNoteColor(isDefaultColor(config, TGConfigKeys.COLOR_PLAY_NOTE, DEFAULT_COLOR_PLAY_NOTE)
					? new UIColorModel(255, 85, 85)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_PLAY_NOTE));
			this.setSelectionColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_SELECTION));
			this.setLoopSMarkerColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_LOOP_S_MARKER));
			this.setLoopEMarkerColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_LOOP_E_MARKER));
			this.setMeasureNumberColor(isDefaultColor(config, TGConfigKeys.COLOR_MEASURE_NUMBER, DEFAULT_COLOR_MEASURE_NUMBER)
					? new UIColorModel(220, 95, 95)
					: config.getColorModelConfigValue(TGConfigKeys.COLOR_MEASURE_NUMBER));
		} else {
			this.setForegroundColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_FOREGROUND));
			this.setBackgroundColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_BACKGROUND));
			this.setBackgroundColorPlaying(config.getColorModelConfigValue(TGConfigKeys.COLOR_BACKGROUND_PLAYING));
			this.setLineColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_LINE));
			this.setLineColorInvalid(config.getColorModelConfigValue(TGConfigKeys.COLOR_LINE_INVALID));
			this.setScoreNoteColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_SCORE_NOTE));
			this.setTabNoteColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_TAB_NOTE));
			this.setPlayNoteColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_PLAY_NOTE));
			this.setSelectionColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_SELECTION));
			this.setLoopSMarkerColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_LOOP_S_MARKER));
			this.setLoopEMarkerColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_LOOP_E_MARKER));
			this.setMeasureNumberColor(config.getColorModelConfigValue(TGConfigKeys.COLOR_MEASURE_NUMBER));
		}
	}

	private boolean isDefaultColor(TGConfigManager config, String key, String defaultRgb) {
		String val = config.getStringValue(key);
		return (val == null || val.trim().equals(defaultRgb));
	}
}
