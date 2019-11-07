// Generated from ProposicionalLogic.g4 by ANTLR 4.5.3

package com.unipampa.edu.proposicional;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProposicionalLogicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, SIMBOLO_PROPOSICIONAL=8, 
		SPACE=9;
	public static final int
		RULE_formula = 0;
	public static final String[] ruleNames = {
		"formula"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'~'", "'^'", "'v'", "'->'", "'<->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "SIMBOLO_PROPOSICIONAL", 
		"SPACE"
	};
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

	@Override
	public String getGrammarFileName() { return "ProposicionalLogic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProposicionalLogicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public TerminalNode SIMBOLO_PROPOSICIONAL() { return getToken(ProposicionalLogicParser.SIMBOLO_PROPOSICIONAL, 0); }
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public List<TerminalNode> SPACE() { return getTokens(ProposicionalLogicParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(ProposicionalLogicParser.SPACE, i);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProposicionalLogicListener ) ((ProposicionalLogicListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProposicionalLogicListener ) ((ProposicionalLogicListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_formula, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			switch (_input.LA(1)) {
			case SIMBOLO_PROPOSICIONAL:
				{
				setState(3);
				match(SIMBOLO_PROPOSICIONAL);
				}
				break;
			case T__0:
				{
				setState(4);
				match(T__0);
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(5);
					match(SPACE);
					}
					}
					setState(10);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(11);
				formula(0);
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(12);
					match(SPACE);
					}
					}
					setState(17);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(18);
				match(T__1);
				}
				break;
			case T__2:
				{
				setState(20);
				match(T__2);
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(21);
					match(SPACE);
					}
					}
					setState(26);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(27);
				formula(5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(90);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(30);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(34);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(31);
							match(SPACE);
							}
							}
							setState(36);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(37);
						match(T__3);
						setState(41);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(38);
							match(SPACE);
							}
							}
							setState(43);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(44);
						formula(5);
						}
						break;
					case 2:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(49);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(46);
							match(SPACE);
							}
							}
							setState(51);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(52);
						match(T__4);
						setState(56);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(53);
							match(SPACE);
							}
							}
							setState(58);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(59);
						formula(4);
						}
						break;
					case 3:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(60);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(64);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(61);
							match(SPACE);
							}
							}
							setState(66);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(67);
						match(T__5);
						setState(71);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(68);
							match(SPACE);
							}
							}
							setState(73);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(74);
						formula(3);
						}
						break;
					case 4:
						{
						_localctx = new FormulaContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(75);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(79);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(76);
							match(SPACE);
							}
							}
							setState(81);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(82);
						match(T__6);
						setState(86);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==SPACE) {
							{
							{
							setState(83);
							match(SPACE);
							}
							}
							setState(88);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(89);
						formula(2);
						}
						break;
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return formula_sempred((FormulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13b\4\2\t\2\3\2\3"+
		"\2\3\2\3\2\7\2\t\n\2\f\2\16\2\f\13\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13"+
		"\2\3\2\3\2\3\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\5\2\37\n\2\3\2\3\2"+
		"\7\2#\n\2\f\2\16\2&\13\2\3\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\2\7"+
		"\2\62\n\2\f\2\16\2\65\13\2\3\2\3\2\7\29\n\2\f\2\16\2<\13\2\3\2\3\2\3\2"+
		"\7\2A\n\2\f\2\16\2D\13\2\3\2\3\2\7\2H\n\2\f\2\16\2K\13\2\3\2\3\2\3\2\7"+
		"\2P\n\2\f\2\16\2S\13\2\3\2\3\2\7\2W\n\2\f\2\16\2Z\13\2\3\2\7\2]\n\2\f"+
		"\2\16\2`\13\2\3\2\2\3\2\3\2\2\2q\2\36\3\2\2\2\4\5\b\2\1\2\5\37\7\n\2\2"+
		"\6\n\7\3\2\2\7\t\7\13\2\2\b\7\3\2\2\2\t\f\3\2\2\2\n\b\3\2\2\2\n\13\3\2"+
		"\2\2\13\r\3\2\2\2\f\n\3\2\2\2\r\21\5\2\2\2\16\20\7\13\2\2\17\16\3\2\2"+
		"\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\24\3\2\2\2\23\21\3\2\2"+
		"\2\24\25\7\4\2\2\25\37\3\2\2\2\26\32\7\5\2\2\27\31\7\13\2\2\30\27\3\2"+
		"\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2"+
		"\2\2\35\37\5\2\2\7\36\4\3\2\2\2\36\6\3\2\2\2\36\26\3\2\2\2\37^\3\2\2\2"+
		" $\f\6\2\2!#\7\13\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2"+
		"\2\2&$\3\2\2\2\'+\7\6\2\2(*\7\13\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3"+
		"\2\2\2,.\3\2\2\2-+\3\2\2\2.]\5\2\2\7/\63\f\5\2\2\60\62\7\13\2\2\61\60"+
		"\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63"+
		"\3\2\2\2\66:\7\7\2\2\679\7\13\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3"+
		"\2\2\2;=\3\2\2\2<:\3\2\2\2=]\5\2\2\6>B\f\4\2\2?A\7\13\2\2@?\3\2\2\2AD"+
		"\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EI\7\b\2\2FH\7\13\2\2"+
		"GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2L]\5\2\2\5"+
		"MQ\f\3\2\2NP\7\13\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2"+
		"\2SQ\3\2\2\2TX\7\t\2\2UW\7\13\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2"+
		"\2\2Y[\3\2\2\2ZX\3\2\2\2[]\5\2\2\4\\ \3\2\2\2\\/\3\2\2\2\\>\3\2\2\2\\"+
		"M\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\3\3\2\2\2`^\3\2\2\2\20\n\21"+
		"\32\36$+\63:BIQX\\^";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}