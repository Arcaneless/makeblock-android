package cc.makeblock.makeblock.engine.utils;

import android.text.TextUtils;
import android.util.Log;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class HanziToPinyin
{
  private static final Collator COLLATOR = Collator.getInstance(Locale.CHINA);
  private static final boolean DEBUG = false;
  private static final String FIRST_PINYIN_UNIHAN = "阿";
  private static final String LAST_PINYIN_UNIHAN = "鿿";
  public static final byte[][] PINYINS;
  private static final String TAG = "HanziToPinyin";
  public static final char[] UNIHANS = { -27073, 21710, 23433, -32594, 20985, 20843, 25344, 25203, -28506, 21241, -27070, 22868, 20283, 23620, -28743, 28780, 24971, 27715, 20907, 30326, 23788, 22163, 20594, 21442, 20179, 25761, 20874, 23934, 26365, 26366, 23652, 21449, -32122, -28737, 20261, 25220, -28826, 25275, 27784, 27785, -27081, 21507, 20805, 25277, 20986, 27451, 25571, 24027, 20997, 21561, 26110, -28620, 21618, 21254, 20945, 31895, 27718, 23828, -28504, 25619, 21649, 21574, 20025, 24403, 20992, 22042, 25189, 28783, 27664, 22002, 30008, 20993, 29241, 19969, 19999, 19996, 21562, 21438, -32751, -30360, 21544, 22810, 22968, -29706, 22848, -26715, 20799, 21457, 24070, 21274, -26402, 20998, 20016, -30331, 20175, 32017, 20245, 26094, 20357, 29976, 20872, 30347, 25096, 32473, 26681, 21039, 24037, 21246, 20272, 29916, 20054, 20851, 20809, 24402, 20008, 21593, 21704, 21645, 20292, 22831, -31968, -29757, -24878, 25323, 20136, 22135, 21503, -24767, 20079, -32079, 24576, 29375, 24031, 28784, 26127, 21529, 19980, 21152, 25099, 27743, -32131, -27082, 24062, 22357, 20866, 20009, 20965, 23010, 22104, 20891, 21652, 24320, 21002, 24572, 23611, 21308, -32626, 21157, 31354, 25248, 25181, 22840, -31569, 23485, 21281, 20111, 22372, 25193, 22403, 26469, 20848, 21879, 25438, -32629, 21202, 23834, 21013, 20457, 22849, -32145, 25769, 21015, 25294, 21026, 28316, 22230, -24679, 30620, 22108, 23048, 30055, 25249, 32599, 21603, 22920, 22475, 23258, 29284, 29483, 20040, 21573, -27160, 30015, 21674, 23424, 21941, 20060, 27665, 21517, -29652, 25720, 21726, 27626, 21999, 25295, -32439, 22241, 22228, 23404, 30098, 23070, 24641, -32515, 22958, 25288, 23330, -25057, 25423, 22236, 23425, 22942, 20892, 32698, 22900, 22907, 30111, -24895, -28467, 21908, -29772, 22929, 25293, 30469, 20051, 25243, 21624, 21943, 21257, 19989, 22248, 21117, 27669, 23000, 20050, -27509, 21078, 20166, 19971, 25488, 21315, 21595, 24708, 30335, 20146, 29381, -32114, 19992, 21306, 23761, 32570, 22795, 21605, 31331, 23046, 24825, 20154, 25172, 26085, -31944, 21433, -28518, 25404, 22567, 23121, 30628, 25468, 20200, 27618, 19977, 26706, 25531, -27222, 26862, 20711, 26432, 31579, 23665, 20260, 24368, 22882, 30003, -31848, 25938, 21319, 23608, 21454, 20070, 21047, -30608, -27159, 21452, -29695, 21550, -29708, 21430, 24554, 25436, -32049, 29435, 22794, 23385, 21766, 20182, 22268, 22349, 27748, 22834, 24529, 29093, 21076, 22825, 26091, 24086, 21381, 22258, 20599, 20984, 28237, 25512, 21534, 20039, 31349, 27498, 24367, 23587, 21361, 26167, 32705, 25373, 20044, 22805, -31118, 20186, 20065, 28785, 20123, 24515, 26143, 20982, 20241, 21505, 21509, 21066, 22339, 20011, 24697, 22830, 24186, 20539, 19968, 22233, 24212, 21727, 20323, 20248, 25180, 22246, 26352, 26197, 31584, 31612, 24064, 28797, 20802, 21288, 20654, 21017, -29380, 24590, 22679, 25166, 25434, 27838, 24352, -27265, -27273, 20299, -30969, -29410, 20105, 20043, 23769, 24226, 20013, 24030, 26417, 25235, 25341, 19987, 22918, -26951, 23442, 21331, 20082, 23447, -28487, 31199, -27461, 21404, 23562, 26152, 20825, -24637, -24636 };
  private static HanziToPinyin sInstance;
  private final boolean mHasChinaCollator;
  
  static
  {
    byte[] arrayOfByte1 = { 65, 78, 0, 0, 0, 0 };
    byte[] arrayOfByte2 = { 65, 79, 0, 0, 0, 0 };
    byte[] arrayOfByte3 = { 66, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte4 = { 66, 69, 73, 0, 0, 0 };
    byte[] arrayOfByte5 = { 66, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte6 = { 66, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte7 = { 66, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte8 = { 66, 73, 78, 0, 0, 0 };
    byte[] arrayOfByte9 = { 66, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte10 = { 67, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte11 = { 67, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte12 = { 67, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte13 = { 67, 72, 65, 78, 0, 0 };
    byte[] arrayOfByte14 = { 67, 72, 65, 79, 0, 0 };
    byte[] arrayOfByte15 = { 67, 72, 69, 0, 0, 0 };
    byte[] arrayOfByte16 = { 83, 72, 69, 78, 0, 0 };
    byte[] arrayOfByte17 = { 67, 72, 69, 78, 71, 0 };
    byte[] arrayOfByte18 = { 67, 72, 79, 78, 71, 0 };
    byte[] arrayOfByte19 = { 67, 72, 79, 85, 0, 0 };
    byte[] arrayOfByte20 = { 67, 72, 85, 65, 0, 0 };
    byte[] arrayOfByte21 = { 67, 72, 85, 65, 73, 0 };
    byte[] arrayOfByte22 = { 67, 72, 85, 65, 78, 71 };
    byte[] arrayOfByte23 = { 67, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte24 = { 67, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte25 = { 67, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte26 = { 67, 85, 73, 0, 0, 0 };
    byte[] arrayOfByte27 = { 67, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte28 = { 67, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte29 = { 68, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte30 = { 68, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte31 = { 68, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte32 = { 68, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte33 = { 68, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte34 = { 68, 73, 65, 79, 0, 0 };
    byte[] arrayOfByte35 = { 68, 73, 85, 0, 0, 0 };
    byte[] arrayOfByte36 = { 68, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte37 = { 68, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte38 = { 68, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte39 = { 68, 85, 65, 78, 0, 0 };
    byte[] arrayOfByte40 = { 68, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte41 = { 70, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte42 = { 70, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte43 = { 70, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte44 = { 70, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte45 = { 70, 73, 65, 79, 0, 0 };
    byte[] arrayOfByte46 = { 70, 79, 0, 0, 0, 0 };
    byte[] arrayOfByte47 = { 70, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte48 = { 71, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte49 = { 71, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte50 = { 71, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte51 = { 71, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte52 = { 71, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte53 = { 71, 69, 73, 0, 0, 0 };
    byte[] arrayOfByte54 = { 71, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte55 = { 71, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte56 = { 71, 85, 65, 73, 0, 0 };
    byte[] arrayOfByte57 = { 71, 85, 65, 78, 0, 0 };
    byte[] arrayOfByte58 = { 72, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte59 = { 72, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte60 = { 72, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte61 = { 72, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte62 = { 72, 77, 0, 0, 0, 0 };
    byte[] arrayOfByte63 = { 72, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte64 = { 72, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte65 = { 72, 85, 65, 78, 71, 0 };
    byte[] arrayOfByte66 = { 72, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte67 = { 72, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte68 = { 74, 73, 65, 0, 0, 0 };
    byte[] arrayOfByte69 = { 74, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte70 = { 74, 73, 78, 0, 0, 0 };
    byte[] arrayOfByte71 = { 74, 73, 79, 78, 71, 0 };
    byte[] arrayOfByte72 = { 74, 85, 69, 0, 0, 0 };
    byte[] arrayOfByte73 = { 75, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte74 = { 75, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte75 = { 75, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte76 = { 75, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte77 = { 75, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte78 = { 75, 85, 65, 78, 71, 0 };
    byte[] arrayOfByte79 = { 75, 85, 73, 0, 0, 0 };
    byte[] arrayOfByte80 = { 75, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte81 = { 75, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte82 = { 76, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte83 = { 76, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte84 = { 76, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte85 = { 76, 69, 73, 0, 0, 0 };
    byte[] arrayOfByte86 = { 76, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte87 = { 76, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte88 = { 76, 73, 65, 0, 0, 0 };
    byte[] arrayOfByte89 = { 76, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte90 = { 76, 73, 78, 0, 0, 0 };
    byte[] arrayOfByte91 = { 76, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte92 = { 76, 79, 0, 0, 0, 0 };
    byte[] arrayOfByte93 = { 76, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte94 = { 76, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte95 = { 76, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte96 = { 76, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte97 = { 77, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte98 = { 77, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte99 = { 77, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte100 = { 77, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte101 = { 77, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte102 = { 77, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte103 = { 77, 73, 85, 0, 0, 0 };
    byte[] arrayOfByte104 = { 77, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte105 = { 78, 0, 0, 0, 0, 0 };
    byte[] arrayOfByte106 = { 78, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte107 = { 78, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte108 = { 78, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte109 = { 78, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte110 = { 78, 73, 85, 0, 0, 0 };
    byte[] arrayOfByte111 = { 78, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte112 = { 78, 85, 69, 0, 0, 0 };
    byte[] arrayOfByte113 = { 78, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte114 = { 79, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte115 = { 80, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte116 = { 80, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte117 = { 80, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte118 = { 80, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte119 = { 80, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte120 = { 80, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte121 = { 80, 73, 65, 79, 0, 0 };
    byte[] arrayOfByte122 = { 80, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte123 = { 80, 73, 78, 0, 0, 0 };
    byte[] arrayOfByte124 = { 80, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte125 = { 81, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte126 = { 81, 73, 65, 78, 0, 0 };
    byte[] arrayOfByte127 = { 81, 73, 65, 79, 0, 0 };
    byte[] arrayOfByte128 = { 81, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte129 = { 81, 85, 69, 0, 0, 0 };
    byte[] arrayOfByte130 = { 81, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte131 = { 82, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte132 = { 82, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte133 = { 82, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte134 = { 82, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte135 = { 82, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte136 = { 82, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte137 = { 82, 85, 65, 78, 0, 0 };
    byte[] arrayOfByte138 = { 83, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte139 = { 83, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte140 = { 83, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte141 = { 83, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte142 = { 83, 72, 65, 0, 0, 0 };
    byte[] arrayOfByte143 = { 83, 72, 65, 78, 0, 0 };
    byte[] arrayOfByte144 = { 83, 72, 65, 79, 0, 0 };
    byte[] arrayOfByte145 = { 83, 72, 69, 0, 0, 0 };
    byte[] arrayOfByte146 = { 83, 72, 69, 78, 0, 0 };
    byte[] arrayOfByte147 = { 83, 72, 73, 0, 0, 0 };
    byte[] arrayOfByte148 = { 83, 72, 85, 65, 73, 0 };
    byte[] arrayOfByte149 = { 83, 72, 85, 65, 78, 71 };
    byte[] arrayOfByte150 = { 83, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte151 = { 83, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte152 = { 83, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte153 = { 83, 85, 73, 0, 0, 0 };
    byte[] arrayOfByte154 = { 84, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte155 = { 84, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte156 = { 84, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte157 = { 84, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte158 = { 84, 65, 79, 0, 0, 0 };
    byte[] arrayOfByte159 = { 84, 73, 0, 0, 0, 0 };
    byte[] arrayOfByte160 = { 84, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte161 = { 84, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte162 = { 84, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte163 = { 87, 65, 73, 0, 0, 0 };
    byte[] arrayOfByte164 = { 87, 69, 78, 0, 0, 0 };
    byte[] arrayOfByte165 = { 88, 73, 65, 78, 0, 0 };
    byte[] arrayOfByte166 = { 88, 73, 65, 79, 0, 0 };
    byte[] arrayOfByte167 = { 88, 73, 69, 0, 0, 0 };
    byte[] arrayOfByte168 = { 88, 73, 78, 0, 0, 0 };
    byte[] arrayOfByte169 = { 88, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte170 = { 88, 73, 79, 78, 71, 0 };
    byte[] arrayOfByte171 = { 88, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte172 = { 89, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte173 = { 89, 65, 78, 0, 0, 0 };
    byte[] arrayOfByte174 = { 89, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte175 = { 89, 69, 0, 0, 0, 0 };
    byte[] arrayOfByte176 = { 89, 73, 78, 71, 0, 0 };
    byte[] arrayOfByte177 = { 89, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte178 = { 89, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte179 = { 89, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte180 = { 89, 85, 78, 0, 0, 0 };
    byte[] arrayOfByte181 = { 90, 65, 0, 0, 0, 0 };
    byte[] arrayOfByte182 = { 90, 65, 78, 71, 0, 0 };
    byte[] arrayOfByte183 = { 90, 69, 78, 71, 0, 0 };
    byte[] arrayOfByte184 = { 90, 72, 65, 0, 0, 0 };
    byte[] arrayOfByte185 = { 90, 72, 65, 73, 0, 0 };
    byte[] arrayOfByte186 = { 90, 72, 65, 78, 0, 0 };
    byte[] arrayOfByte187 = { 67, 72, 65, 78, 71, 0 };
    byte[] arrayOfByte188 = { 90, 72, 65, 79, 0, 0 };
    byte[] arrayOfByte189 = { 90, 72, 69, 0, 0, 0 };
    byte[] arrayOfByte190 = { 83, 72, 73, 0, 0, 0 };
    byte[] arrayOfByte191 = { 90, 72, 73, 0, 0, 0 };
    byte[] arrayOfByte192 = { 90, 72, 85, 0, 0, 0 };
    byte[] arrayOfByte193 = { 90, 72, 85, 65, 0, 0 };
    byte[] arrayOfByte194 = { 90, 72, 85, 65, 73, 0 };
    byte[] arrayOfByte195 = { 90, 72, 85, 65, 78, 0 };
    byte[] arrayOfByte196 = { 90, 72, 85, 73, 0, 0 };
    byte[] arrayOfByte197 = { 90, 72, 85, 78, 0, 0 };
    byte[] arrayOfByte198 = { 90, 79, 78, 71, 0, 0 };
    byte[] arrayOfByte199 = { 90, 79, 85, 0, 0, 0 };
    byte[] arrayOfByte200 = { 90, 85, 0, 0, 0, 0 };
    byte[] arrayOfByte201 = { 90, 85, 73, 0, 0, 0 };
    byte[] arrayOfByte202 = { 90, 85, 79, 0, 0, 0 };
    byte[] arrayOfByte203 = { 0, 0, 0, 0, 0, 0 };
    byte[] arrayOfByte204 = { 83, 72, 65, 78, 0, 0 };
    byte[] arrayOfByte205 = { 0, 0, 0, 0, 0, 0 };
    PINYINS = new byte[][] { { 65, 0, 0, 0, 0, 0 }, { 65, 73, 0, 0, 0, 0 }, arrayOfByte1, { 65, 78, 71, 0, 0, 0 }, arrayOfByte2, { 66, 65, 0, 0, 0, 0 }, { 66, 65, 73, 0, 0, 0 }, { 66, 65, 78, 0, 0, 0 }, { 66, 65, 78, 71, 0, 0 }, arrayOfByte3, arrayOfByte4, { 66, 69, 78, 0, 0, 0 }, arrayOfByte5, arrayOfByte6, { 66, 73, 65, 78, 0, 0 }, { 66, 73, 65, 79, 0, 0 }, arrayOfByte7, arrayOfByte8, { 66, 73, 78, 71, 0, 0 }, { 66, 79, 0, 0, 0, 0 }, arrayOfByte9, { 67, 65, 0, 0, 0, 0 }, { 67, 65, 73, 0, 0, 0 }, arrayOfByte10, { 67, 65, 78, 71, 0, 0 }, arrayOfByte11, { 67, 69, 0, 0, 0, 0 }, { 67, 69, 78, 0, 0, 0 }, arrayOfByte12, { 90, 69, 78, 71, 0, 0 }, { 67, 69, 78, 71, 0, 0 }, { 67, 72, 65, 0, 0, 0 }, { 67, 72, 65, 73, 0, 0 }, arrayOfByte13, { 67, 72, 65, 78, 71, 0 }, arrayOfByte14, arrayOfByte15, { 67, 72, 69, 78, 0, 0 }, arrayOfByte16, { 67, 72, 69, 78, 0, 0 }, arrayOfByte17, { 67, 72, 73, 0, 0, 0 }, arrayOfByte18, arrayOfByte19, { 67, 72, 85, 0, 0, 0 }, arrayOfByte20, arrayOfByte21, { 67, 72, 85, 65, 78, 0 }, arrayOfByte22, { 67, 72, 85, 73, 0, 0 }, { 67, 72, 85, 78, 0, 0 }, { 67, 72, 85, 79, 0, 0 }, arrayOfByte23, arrayOfByte24, { 67, 79, 85, 0, 0, 0 }, arrayOfByte25, { 67, 85, 65, 78, 0, 0 }, arrayOfByte26, arrayOfByte27, arrayOfByte28, arrayOfByte29, { 68, 65, 73, 0, 0, 0 }, arrayOfByte30, arrayOfByte31, arrayOfByte32, { 68, 69, 0, 0, 0, 0 }, arrayOfByte33, { 68, 69, 78, 71, 0, 0 }, { 68, 73, 0, 0, 0, 0 }, { 68, 73, 65, 0, 0, 0 }, { 68, 73, 65, 78, 0, 0 }, arrayOfByte34, { 68, 73, 69, 0, 0, 0 }, { 68, 73, 78, 71, 0, 0 }, arrayOfByte35, arrayOfByte36, arrayOfByte37, arrayOfByte38, arrayOfByte39, { 68, 85, 73, 0, 0, 0 }, { 68, 85, 78, 0, 0, 0 }, arrayOfByte40, { 69, 0, 0, 0, 0, 0 }, { 69, 73, 0, 0, 0, 0 }, { 69, 78, 0, 0, 0, 0 }, { 69, 78, 71, 0, 0, 0 }, { 69, 82, 0, 0, 0, 0 }, { 70, 65, 0, 0, 0, 0 }, arrayOfByte41, arrayOfByte42, { 70, 69, 73, 0, 0, 0 }, arrayOfByte43, arrayOfByte44, arrayOfByte45, arrayOfByte46, arrayOfByte47, { 70, 85, 0, 0, 0, 0 }, arrayOfByte48, arrayOfByte49, { 71, 65, 78, 0, 0, 0 }, arrayOfByte50, arrayOfByte51, arrayOfByte52, arrayOfByte53, { 71, 69, 78, 0, 0, 0 }, arrayOfByte54, { 71, 79, 78, 71, 0, 0 }, arrayOfByte55, { 71, 85, 0, 0, 0, 0 }, { 71, 85, 65, 0, 0, 0 }, arrayOfByte56, arrayOfByte57, { 71, 85, 65, 78, 71, 0 }, { 71, 85, 73, 0, 0, 0 }, { 71, 85, 78, 0, 0, 0 }, { 71, 85, 79, 0, 0, 0 }, arrayOfByte58, arrayOfByte59, { 72, 65, 78, 0, 0, 0 }, arrayOfByte60, { 72, 65, 79, 0, 0, 0 }, arrayOfByte61, { 72, 69, 73, 0, 0, 0 }, { 72, 69, 78, 0, 0, 0 }, { 72, 69, 78, 71, 0, 0 }, arrayOfByte62, { 72, 79, 78, 71, 0, 0 }, arrayOfByte63, arrayOfByte64, { 72, 85, 65, 0, 0, 0 }, { 72, 85, 65, 73, 0, 0 }, { 72, 85, 65, 78, 0, 0 }, arrayOfByte65, { 72, 85, 73, 0, 0, 0 }, arrayOfByte66, arrayOfByte67, { 74, 73, 0, 0, 0, 0 }, arrayOfByte68, { 74, 73, 65, 78, 0, 0 }, { 74, 73, 65, 78, 71, 0 }, { 74, 73, 65, 79, 0, 0 }, arrayOfByte69, arrayOfByte70, { 74, 73, 78, 71, 0, 0 }, arrayOfByte71, { 74, 73, 85, 0, 0, 0 }, { 74, 85, 0, 0, 0, 0 }, { 74, 85, 65, 78, 0, 0 }, arrayOfByte72, { 74, 85, 78, 0, 0, 0 }, { 75, 65, 0, 0, 0, 0 }, { 75, 65, 73, 0, 0, 0 }, { 75, 65, 78, 0, 0, 0 }, arrayOfByte73, { 75, 65, 79, 0, 0, 0 }, arrayOfByte74, arrayOfByte75, arrayOfByte76, arrayOfByte77, { 75, 79, 85, 0, 0, 0 }, { 75, 85, 0, 0, 0, 0 }, { 75, 85, 65, 0, 0, 0 }, { 75, 85, 65, 73, 0, 0 }, { 75, 85, 65, 78, 0, 0 }, arrayOfByte78, arrayOfByte79, arrayOfByte80, arrayOfByte81, arrayOfByte82, arrayOfByte83, arrayOfByte84, { 76, 65, 78, 71, 0, 0 }, { 76, 65, 79, 0, 0, 0 }, { 76, 69, 0, 0, 0, 0 }, arrayOfByte85, arrayOfByte86, arrayOfByte87, arrayOfByte88, { 76, 73, 65, 78, 0, 0 }, { 76, 73, 65, 78, 71, 0 }, { 76, 73, 65, 79, 0, 0 }, arrayOfByte89, arrayOfByte90, arrayOfByte91, { 76, 73, 85, 0, 0, 0 }, arrayOfByte92, arrayOfByte93, arrayOfByte94, { 76, 85, 0, 0, 0, 0 }, { 76, 85, 65, 78, 0, 0 }, { 76, 85, 69, 0, 0, 0 }, arrayOfByte95, arrayOfByte96, { 77, 0, 0, 0, 0, 0 }, arrayOfByte97, { 77, 65, 73, 0, 0, 0 }, { 77, 65, 78, 0, 0, 0 }, arrayOfByte98, arrayOfByte99, { 77, 69, 0, 0, 0, 0 }, { 77, 69, 73, 0, 0, 0 }, { 77, 69, 78, 0, 0, 0 }, { 77, 69, 78, 71, 0, 0 }, arrayOfByte100, { 77, 73, 65, 78, 0, 0 }, { 77, 73, 65, 79, 0, 0 }, arrayOfByte101, { 77, 73, 78, 0, 0, 0 }, arrayOfByte102, arrayOfByte103, { 77, 79, 0, 0, 0, 0 }, arrayOfByte104, { 77, 85, 0, 0, 0, 0 }, arrayOfByte105, { 78, 65, 0, 0, 0, 0 }, arrayOfByte106, { 78, 65, 78, 0, 0, 0 }, { 78, 65, 78, 71, 0, 0 }, arrayOfByte107, arrayOfByte108, { 78, 69, 73, 0, 0, 0 }, arrayOfByte109, { 78, 69, 78, 71, 0, 0 }, { 78, 73, 0, 0, 0, 0 }, { 78, 73, 65, 78, 0, 0 }, { 78, 73, 65, 78, 71, 0 }, { 78, 73, 65, 79, 0, 0 }, { 78, 73, 69, 0, 0, 0 }, { 78, 73, 78, 0, 0, 0 }, { 78, 73, 78, 71, 0, 0 }, arrayOfByte110, { 78, 79, 78, 71, 0, 0 }, { 78, 79, 85, 0, 0, 0 }, arrayOfByte111, { 78, 85, 65, 78, 0, 0 }, arrayOfByte112, arrayOfByte113, { 78, 85, 79, 0, 0, 0 }, { 79, 0, 0, 0, 0, 0 }, arrayOfByte114, arrayOfByte115, arrayOfByte116, arrayOfByte117, { 80, 65, 78, 71, 0, 0 }, { 80, 65, 79, 0, 0, 0 }, { 80, 69, 73, 0, 0, 0 }, arrayOfByte118, arrayOfByte119, arrayOfByte120, { 80, 73, 65, 78, 0, 0 }, arrayOfByte121, arrayOfByte122, arrayOfByte123, arrayOfByte124, { 80, 79, 0, 0, 0, 0 }, { 80, 79, 85, 0, 0, 0 }, { 80, 85, 0, 0, 0, 0 }, arrayOfByte125, { 81, 73, 65, 0, 0, 0 }, arrayOfByte126, { 81, 73, 65, 78, 71, 0 }, arrayOfByte127, { 81, 73, 69, 0, 0, 0 }, { 81, 73, 78, 0, 0, 0 }, arrayOfByte128, { 81, 73, 79, 78, 71, 0 }, { 81, 73, 85, 0, 0, 0 }, { 81, 85, 0, 0, 0, 0 }, { 81, 85, 65, 78, 0, 0 }, arrayOfByte129, arrayOfByte130, { 82, 65, 78, 0, 0, 0 }, { 82, 65, 78, 71, 0, 0 }, arrayOfByte131, arrayOfByte132, arrayOfByte133, { 82, 69, 78, 71, 0, 0 }, { 82, 73, 0, 0, 0, 0 }, arrayOfByte134, arrayOfByte135, arrayOfByte136, { 82, 85, 65, 0, 0, 0 }, arrayOfByte137, { 82, 85, 73, 0, 0, 0 }, { 82, 85, 78, 0, 0, 0 }, { 82, 85, 79, 0, 0, 0 }, arrayOfByte138, { 83, 65, 73, 0, 0, 0 }, arrayOfByte139, { 83, 65, 78, 71, 0, 0 }, arrayOfByte140, { 83, 69, 0, 0, 0, 0 }, { 83, 69, 78, 0, 0, 0 }, arrayOfByte141, arrayOfByte142, { 83, 72, 65, 73, 0, 0 }, arrayOfByte143, { 83, 72, 65, 78, 71, 0 }, arrayOfByte144, arrayOfByte145, arrayOfByte146, { 88, 73, 78, 0, 0, 0 }, { 83, 72, 69, 78, 0, 0 }, { 83, 72, 69, 78, 71, 0 }, arrayOfByte147, { 83, 72, 79, 85, 0, 0 }, { 83, 72, 85, 0, 0, 0 }, { 83, 72, 85, 65, 0, 0 }, arrayOfByte148, { 83, 72, 85, 65, 78, 0 }, arrayOfByte149, { 83, 72, 85, 73, 0, 0 }, { 83, 72, 85, 78, 0, 0 }, { 83, 72, 85, 79, 0, 0 }, arrayOfByte150, arrayOfByte151, { 83, 79, 85, 0, 0, 0 }, arrayOfByte152, { 83, 85, 65, 78, 0, 0 }, arrayOfByte153, { 83, 85, 78, 0, 0, 0 }, { 83, 85, 79, 0, 0, 0 }, arrayOfByte154, arrayOfByte155, arrayOfByte156, arrayOfByte157, arrayOfByte158, { 84, 69, 0, 0, 0, 0 }, { 84, 69, 78, 71, 0, 0 }, arrayOfByte159, { 84, 73, 65, 78, 0, 0 }, { 84, 73, 65, 79, 0, 0 }, { 84, 73, 69, 0, 0, 0 }, { 84, 73, 78, 71, 0, 0 }, arrayOfByte160, arrayOfByte161, { 84, 85, 0, 0, 0, 0 }, { 84, 85, 65, 78, 0, 0 }, { 84, 85, 73, 0, 0, 0 }, { 84, 85, 78, 0, 0, 0 }, arrayOfByte162, { 87, 65, 0, 0, 0, 0 }, arrayOfByte163, { 87, 65, 78, 0, 0, 0 }, { 87, 65, 78, 71, 0, 0 }, { 87, 69, 73, 0, 0, 0 }, arrayOfByte164, { 87, 69, 78, 71, 0, 0 }, { 87, 79, 0, 0, 0, 0 }, { 87, 85, 0, 0, 0, 0 }, { 88, 73, 0, 0, 0, 0 }, { 88, 73, 65, 0, 0, 0 }, arrayOfByte165, { 88, 73, 65, 78, 71, 0 }, arrayOfByte166, arrayOfByte167, arrayOfByte168, arrayOfByte169, arrayOfByte170, { 88, 73, 85, 0, 0, 0 }, arrayOfByte171, { 88, 85, 65, 78, 0, 0 }, { 88, 85, 69, 0, 0, 0 }, { 88, 85, 78, 0, 0, 0 }, arrayOfByte172, arrayOfByte173, arrayOfByte174, { 89, 65, 79, 0, 0, 0 }, arrayOfByte175, { 89, 73, 0, 0, 0, 0 }, { 89, 73, 78, 0, 0, 0 }, arrayOfByte176, { 89, 79, 0, 0, 0, 0 }, arrayOfByte177, { 89, 79, 85, 0, 0, 0 }, arrayOfByte178, { 89, 85, 65, 78, 0, 0 }, { 89, 85, 69, 0, 0, 0 }, arrayOfByte179, { 74, 85, 78, 0, 0, 0 }, arrayOfByte180, arrayOfByte181, { 90, 65, 73, 0, 0, 0 }, { 90, 65, 78, 0, 0, 0 }, arrayOfByte182, { 90, 65, 79, 0, 0, 0 }, { 90, 69, 0, 0, 0, 0 }, { 90, 69, 73, 0, 0, 0 }, { 90, 69, 78, 0, 0, 0 }, arrayOfByte183, arrayOfByte184, arrayOfByte185, arrayOfByte186, { 90, 72, 65, 78, 71, 0 }, arrayOfByte187, { 90, 72, 65, 78, 71, 0 }, arrayOfByte188, arrayOfByte189, { 90, 72, 69, 78, 0, 0 }, { 90, 72, 69, 78, 71, 0 }, { 90, 72, 73, 0, 0, 0 }, arrayOfByte190, arrayOfByte191, { 90, 72, 79, 78, 71, 0 }, { 90, 72, 79, 85, 0, 0 }, arrayOfByte192, arrayOfByte193, arrayOfByte194, arrayOfByte195, { 90, 72, 85, 65, 78, 71 }, arrayOfByte196, arrayOfByte197, { 90, 72, 85, 79, 0, 0 }, { 90, 73, 0, 0, 0, 0 }, arrayOfByte198, arrayOfByte199, arrayOfByte200, { 90, 85, 65, 78, 0, 0 }, arrayOfByte201, { 90, 85, 78, 0, 0, 0 }, arrayOfByte202, arrayOfByte203, arrayOfByte204, arrayOfByte205 };
  }
  
  protected HanziToPinyin(boolean paramBoolean)
  {
    this.mHasChinaCollator = paramBoolean;
  }
  
  private void addToken(StringBuilder paramStringBuilder, ArrayList<Token> paramArrayList, int paramInt)
  {
    String str = paramStringBuilder.toString();
    paramArrayList.add(new Token(paramInt, str, str));
    paramStringBuilder.setLength(0);
  }
  
  private static boolean doSelfValidation()
  {
    char c1 = UNIHANS[0];
    Object localObject = Character.toString(c1);
    char[] arrayOfChar = UNIHANS;
    int j = arrayOfChar.length;
    int i = 0;
    if (i < j)
    {
      char c2 = arrayOfChar[i];
      if (c1 == c2) {}
      for (;;)
      {
        i += 1;
        break;
        String str = Character.toString(c2);
        if (COLLATOR.compare((String)localObject, str) >= 0)
        {
          Log.e("HanziToPinyin", "Internal error in Unihan table. The last string \"" + (String)localObject + "\" is greater than currentForm string \"" + str + "\".");
          return false;
        }
        localObject = str;
      }
    }
    return true;
  }
  
  public static HanziToPinyin getInstance()
  {
    for (;;)
    {
      Object localObject1;
      Locale localLocale;
      int i;
      try
      {
        if (sInstance != null)
        {
          localObject1 = sInstance;
          return (HanziToPinyin)localObject1;
        }
        localObject1 = Collator.getAvailableLocales();
        localLocale = new Locale("zh");
      }
      finally {}
      if (i < localObject1.length)
      {
        if ((localObject1[i].equals(Locale.CHINA)) || (localObject1[i].equals(localLocale)))
        {
          sInstance = new HanziToPinyin(true);
          localObject1 = sInstance;
          return (HanziToPinyin)localObject1;
        }
        i += 1;
      }
      else
      {
        Log.w("HanziToPinyin", "There is no Chinese collator, HanziToPinyin is disabled");
        sInstance = new HanziToPinyin(false);
        HanziToPinyin localHanziToPinyin = sInstance;
        return localHanziToPinyin;
        i = 0;
      }
    }
  }
  
  private Token getToken(char paramChar)
  {
    Token localToken = new Token();
    Object localObject = Character.toString(paramChar);
    localToken.source = ((String)localObject);
    int i = -1;
    if (paramChar < 'Ā')
    {
      localToken.type = 1;
      localToken.target = ((String)localObject);
    }
    do
    {
      return localToken;
      int j = COLLATOR.compare((String)localObject, "阿");
      if (j < 0)
      {
        localToken.type = 3;
        localToken.target = ((String)localObject);
        return localToken;
      }
      int m;
      int k;
      int i1;
      int n;
      if (j == 0)
      {
        localToken.type = 2;
        i = 0;
        localToken.type = 2;
        m = j;
        k = i;
        if (i < 0)
        {
          i1 = 0;
          n = UNIHANS.length - 1;
        }
      }
      for (;;)
      {
        m = j;
        k = i;
        if (i1 <= n)
        {
          i = (i1 + n) / 2;
          String str = Character.toString(UNIHANS[i]);
          j = COLLATOR.compare((String)localObject, str);
          if (j == 0)
          {
            k = i;
            m = j;
          }
        }
        else
        {
          i = k;
          if (m < 0) {
            i = k - 1;
          }
          localObject = new StringBuilder();
          j = 0;
          while ((j < PINYINS[i].length) && (PINYINS[i][j] != 0))
          {
            ((StringBuilder)localObject).append((char)PINYINS[i][j]);
            j += 1;
          }
          k = COLLATOR.compare((String)localObject, "鿿");
          if (k > 0)
          {
            localToken.type = 3;
            localToken.target = ((String)localObject);
            return localToken;
          }
          j = k;
          if (k != 0) {
            break;
          }
          localToken.type = 2;
          i = UNIHANS.length - 1;
          j = k;
          break;
        }
        if (j > 0) {
          i1 = i + 1;
        } else {
          n = i - 1;
        }
      }
      localToken.target = ((StringBuilder)localObject).toString();
    } while (!TextUtils.isEmpty(localToken.target));
    localToken.type = 3;
    localToken.target = localToken.source;
    return localToken;
  }
  
  public ArrayList<Token> get(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((!this.mHasChinaCollator) || (TextUtils.isEmpty(paramString))) {}
    StringBuilder localStringBuilder;
    int k;
    do
    {
      return localArrayList;
      int m = paramString.length();
      localStringBuilder = new StringBuilder();
      k = 1;
      int j = 0;
      if (j < m)
      {
        char c = paramString.charAt(j);
        int i;
        if (c == ' ')
        {
          i = k;
          if (localStringBuilder.length() > 0)
          {
            addToken(localStringBuilder, localArrayList, k);
            i = k;
          }
        }
        for (;;)
        {
          j += 1;
          k = i;
          break;
          if (c < 'Ā')
          {
            if ((k != 1) && (localStringBuilder.length() > 0)) {
              addToken(localStringBuilder, localArrayList, k);
            }
            i = 1;
            localStringBuilder.append(c);
          }
          else
          {
            Token localToken = getToken(c);
            if (localToken.type == 2)
            {
              if (localStringBuilder.length() > 0) {
                addToken(localStringBuilder, localArrayList, k);
              }
              localArrayList.add(localToken);
              i = 2;
            }
            else
            {
              if ((k != localToken.type) && (localStringBuilder.length() > 0)) {
                addToken(localStringBuilder, localArrayList, k);
              }
              i = localToken.type;
              localStringBuilder.append(c);
            }
          }
        }
      }
    } while (localStringBuilder.length() <= 0);
    addToken(localStringBuilder, localArrayList, k);
    return localArrayList;
  }
  
  public static class Token
  {
    public static final int LATIN = 1;
    public static final int PINYIN = 2;
    public static final String SEPARATOR = " ";
    public static final int UNKNOWN = 3;
    public String source;
    public String target;
    public int type;
    
    public Token() {}
    
    public Token(int paramInt, String paramString1, String paramString2)
    {
      this.type = paramInt;
      this.source = paramString1;
      this.target = paramString2;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\HanziToPinyin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */