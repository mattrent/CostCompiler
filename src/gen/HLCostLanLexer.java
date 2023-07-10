package gen;// Generated from C:/Users/simon/Desktop/CostCompiler/src\HLCostLan.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class HLCostLanLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, ID=21, NUMBER=22, WS=23, LINECOMMENTS=24, 
		BLOCKCOMMENTS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "CHAR", "ID", "DIGIT", "NUMBER", "WS", "LINECOMMENTS", 
			"BLOCKCOMMENTS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'=>'", "'{'", "'}'", "','", "'call'", "'if'", "'else'", 
			"'for'", "'in'", "'0'", "'+'", "'-'", "'>'", "'=='", "'>='", "'&&'", 
			"'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "ID", "NUMBER", 
			"WS", "LINECOMMENTS", "BLOCKCOMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public HLCostLanLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "HLCostLan.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u00a0\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005"+
		"\u0015s\b\u0015\n\u0015\f\u0015v\t\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0004\u0017{\b\u0017\u000b\u0017\f\u0017|\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0005\u0019\u0087\b\u0019\n\u0019\f\u0019\u008a\t\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u0097\b\u001a\n"+
		"\u001a\f\u001a\u009a\t\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0000\u0000\u001b\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0000+\u0015-\u0000/\u00161\u00173\u00185\u0019\u0001"+
		"\u0000\u0006\u0002\u0000AZaz\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r"+
		"\u0002\u0000**//\u0001\u0000**\u0001\u0000//\u00a5\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u0000"+
		"5\u0001\u0000\u0000\u0000\u00017\u0001\u0000\u0000\u0000\u00039\u0001"+
		"\u0000\u0000\u0000\u0005;\u0001\u0000\u0000\u0000\u0007>\u0001\u0000\u0000"+
		"\u0000\t@\u0001\u0000\u0000\u0000\u000bB\u0001\u0000\u0000\u0000\rD\u0001"+
		"\u0000\u0000\u0000\u000fI\u0001\u0000\u0000\u0000\u0011L\u0001\u0000\u0000"+
		"\u0000\u0013Q\u0001\u0000\u0000\u0000\u0015U\u0001\u0000\u0000\u0000\u0017"+
		"X\u0001\u0000\u0000\u0000\u0019Z\u0001\u0000\u0000\u0000\u001b\\\u0001"+
		"\u0000\u0000\u0000\u001d^\u0001\u0000\u0000\u0000\u001f`\u0001\u0000\u0000"+
		"\u0000!c\u0001\u0000\u0000\u0000#f\u0001\u0000\u0000\u0000%i\u0001\u0000"+
		"\u0000\u0000\'k\u0001\u0000\u0000\u0000)m\u0001\u0000\u0000\u0000+o\u0001"+
		"\u0000\u0000\u0000-w\u0001\u0000\u0000\u0000/z\u0001\u0000\u0000\u0000"+
		"1~\u0001\u0000\u0000\u00003\u0082\u0001\u0000\u0000\u00005\u008d\u0001"+
		"\u0000\u0000\u000078\u0005(\u0000\u00008\u0002\u0001\u0000\u0000\u0000"+
		"9:\u0005)\u0000\u0000:\u0004\u0001\u0000\u0000\u0000;<\u0005=\u0000\u0000"+
		"<=\u0005>\u0000\u0000=\u0006\u0001\u0000\u0000\u0000>?\u0005{\u0000\u0000"+
		"?\b\u0001\u0000\u0000\u0000@A\u0005}\u0000\u0000A\n\u0001\u0000\u0000"+
		"\u0000BC\u0005,\u0000\u0000C\f\u0001\u0000\u0000\u0000DE\u0005c\u0000"+
		"\u0000EF\u0005a\u0000\u0000FG\u0005l\u0000\u0000GH\u0005l\u0000\u0000"+
		"H\u000e\u0001\u0000\u0000\u0000IJ\u0005i\u0000\u0000JK\u0005f\u0000\u0000"+
		"K\u0010\u0001\u0000\u0000\u0000LM\u0005e\u0000\u0000MN\u0005l\u0000\u0000"+
		"NO\u0005s\u0000\u0000OP\u0005e\u0000\u0000P\u0012\u0001\u0000\u0000\u0000"+
		"QR\u0005f\u0000\u0000RS\u0005o\u0000\u0000ST\u0005r\u0000\u0000T\u0014"+
		"\u0001\u0000\u0000\u0000UV\u0005i\u0000\u0000VW\u0005n\u0000\u0000W\u0016"+
		"\u0001\u0000\u0000\u0000XY\u00050\u0000\u0000Y\u0018\u0001\u0000\u0000"+
		"\u0000Z[\u0005+\u0000\u0000[\u001a\u0001\u0000\u0000\u0000\\]\u0005-\u0000"+
		"\u0000]\u001c\u0001\u0000\u0000\u0000^_\u0005>\u0000\u0000_\u001e\u0001"+
		"\u0000\u0000\u0000`a\u0005=\u0000\u0000ab\u0005=\u0000\u0000b \u0001\u0000"+
		"\u0000\u0000cd\u0005>\u0000\u0000de\u0005=\u0000\u0000e\"\u0001\u0000"+
		"\u0000\u0000fg\u0005&\u0000\u0000gh\u0005&\u0000\u0000h$\u0001\u0000\u0000"+
		"\u0000ij\u0005*\u0000\u0000j&\u0001\u0000\u0000\u0000kl\u0005/\u0000\u0000"+
		"l(\u0001\u0000\u0000\u0000mn\u0007\u0000\u0000\u0000n*\u0001\u0000\u0000"+
		"\u0000ot\u0003)\u0014\u0000ps\u0003)\u0014\u0000qs\u0003-\u0016\u0000"+
		"rp\u0001\u0000\u0000\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000"+
		"\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u,\u0001\u0000"+
		"\u0000\u0000vt\u0001\u0000\u0000\u0000wx\u000209\u0000x.\u0001\u0000\u0000"+
		"\u0000y{\u0003-\u0016\u0000zy\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000"+
		"\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}0\u0001\u0000"+
		"\u0000\u0000~\u007f\u0007\u0001\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0006\u0018\u0000\u0000\u00812\u0001\u0000\u0000\u0000"+
		"\u0082\u0083\u0005/\u0000\u0000\u0083\u0084\u0005/\u0000\u0000\u0084\u0088"+
		"\u0001\u0000\u0000\u0000\u0085\u0087\b\u0002\u0000\u0000\u0086\u0085\u0001"+
		"\u0000\u0000\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008b\u0001"+
		"\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008c\u0006"+
		"\u0019\u0000\u0000\u008c4\u0001\u0000\u0000\u0000\u008d\u008e\u0005/\u0000"+
		"\u0000\u008e\u008f\u0005*\u0000\u0000\u008f\u0098\u0001\u0000\u0000\u0000"+
		"\u0090\u0097\b\u0003\u0000\u0000\u0091\u0092\u0005/\u0000\u0000\u0092"+
		"\u0097\b\u0004\u0000\u0000\u0093\u0094\u0005*\u0000\u0000\u0094\u0097"+
		"\b\u0005\u0000\u0000\u0095\u0097\u00035\u001a\u0000\u0096\u0090\u0001"+
		"\u0000\u0000\u0000\u0096\u0091\u0001\u0000\u0000\u0000\u0096\u0093\u0001"+
		"\u0000\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0097\u009a\u0001"+
		"\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001"+
		"\u0000\u0000\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0005*\u0000\u0000\u009c\u009d\u0005/\u0000"+
		"\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0006\u001a\u0000"+
		"\u0000\u009f6\u0001\u0000\u0000\u0000\u0007\u0000rt|\u0088\u0096\u0098"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}