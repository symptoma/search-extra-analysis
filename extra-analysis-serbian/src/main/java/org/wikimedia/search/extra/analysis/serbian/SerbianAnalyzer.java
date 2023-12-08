package org.wikimedia.search.extra.analysis.serbian;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "OCP_OVERLY_CONCRETE_PARAMETER", justification = "Standard pattern for analyzers.")
public final class SerbianAnalyzer extends Analyzer {

    private final CharArraySet stemExclusionTable;

    public SerbianAnalyzer() {
        this(CharArraySet.EMPTY_SET);
    }

    public SerbianAnalyzer(CharArraySet stemExclusionTable) {
        super();
        this.stemExclusionTable = CharArraySet.unmodifiableSet(CharArraySet.copy(stemExclusionTable));
    }

    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(source);
        if (!this.stemExclusionTable.isEmpty()) {
            result = new SetKeywordMarkerFilter(result, stemExclusionTable);
        }
        result = new SerbianStemmerTokenFilter(result);
        return new TokenStreamComponents(source, result);
    }

    protected TokenStream normalize(String fieldName, TokenStream in) {
        return new LowerCaseFilter(in);
    }
}
