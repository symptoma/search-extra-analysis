package org.wikimedia.search.extra.analysis.serbian;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

public class SerbianAnalyzerProvider extends AbstractIndexAnalyzerProvider<SerbianAnalyzer> {

    private final SerbianAnalyzer analyzer;

    public SerbianAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        analyzer = new SerbianAnalyzer();
    }

    @Override
    public SerbianAnalyzer get() {
        return this.analyzer;
    }
}
