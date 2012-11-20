package banner.eval.uima;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;


import banner.eval.BANNER;
import banner.postprocessing.PostProcessor;
import banner.tagging.CRFTagger;
import banner.tagging.dictionary.DictionaryTagger;
import banner.tokenization.Tokenizer;
import banner.types.Mention;
import banner.types.Sentence;
import dragon.nlp.tool.Tagger;
import dragon.nlp.tool.lemmatiser.EngLemmatiser;

public class BANNERWrapper {

	Tokenizer tokenizer;
	DictionaryTagger dictionary;
	HierarchicalConfiguration config;
	// Dataset dataset;
	EngLemmatiser lemmatiser;
	Tagger posTagger;
	CRFTagger tagger;
	PostProcessor postProcessor;
    private final String configPrefix = "config/";
    private final String modelPrefix = "../banner/src/main/resources/output/";
	public Map<String, String> getAnnotations(String docText) {
		Map<String, String> annotSpans = new HashMap<String, String>();
		Scanner sc = new Scanner(docText);
		int count = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim();
			if (line.length() > 0) {
				// String[] split = line.split("\\t");
				Sentence sentence = new Sentence(Integer.toString(count), "",
						line);
				sentence = BANNER.process(tagger, tokenizer, postProcessor,
						sentence);
				for (Mention mention : sentence.getMentions()) {
					annotSpans.put(
							mention.getText(),
							mention.getEntityType().getText());
					// Gene g = new Gene(jcas, count+mention.getStartChar(),
					// count+mention.getEndChar());
					// g.setId("");
					// g.addToIndexes();
					/*
					 * StringBuilder output = new StringBuilder();
					 * output.append(line); // sentence identifier
					 * output.append("\t");
					 * output.append(mention.getEntityType());
					 * output.append("\t");
					 * output.append(mention.getStartChar());
					 * output.append("\t"); output.append(mention.getEndChar());
					 * output.append("\t"); output.append(mention.getText());
					 * System.out.println(output.toString());
					 */
				}
			}
			count += line.length() + 1;
		}
		return annotSpans;
	}

	public void initialize(String configFilePath, String modelFilePath) throws IOException, ConfigurationException {
		long start = System.currentTimeMillis();
		File configFile = new File(configPrefix +configFilePath);
		File modelFile = new File(modelPrefix + modelFilePath);
		//ObjectInputStream ois = new ObjectInputStream(url.openStream());
        //chunker = (Chunker) ois.readObject();
        //Streams.closeQuietly(ois);
				this.config = new XMLConfiguration(configPrefix +configFilePath);
			
			// dataset = BANNER.getDataset(config);
			tokenizer = BANNER.getTokenizer(config);
			dictionary = BANNER.getDictionary(config);
			lemmatiser = BANNER.getLemmatiser(config);
			posTagger = BANNER.getPosTagger(config);
			postProcessor = BANNER.getPostProcessor(config);
			HierarchicalConfiguration localConfig = config
					.configurationAt(BANNER.class.getPackage().getName());
			String modelFilename = modelPrefix + modelFilePath;
			System.out.println("Model: " + modelFilename);
			tagger = CRFTagger.load(new File(modelFilename), lemmatiser,
					posTagger, dictionary);
			System.out.println("Loaded model: "
					+ (System.currentTimeMillis() - start) + "ms");

		/*
		 * BANNER.logInput(dataset.getSentences(), config);
		 * System.out.println("Completed input: " + (System.currentTimeMillis()
		 * - start)); Performance performance = test(dataset, tagger, config);
		 * performance.print();
		 */
	}

	/*
	 * private DictionaryTagger loadDictionary(UimaContext aContext) { try {
	 * //String dict = aContext.getResourceFilePath("dict"); String
	 * tokenizerName = aContext.getResourceFilePath("tokenizer"); tokenizer =
	 * (Tokenizer)Class.forName(tokenizerName).newInstance();
	 * 
	 * String dictionaryName = aContext.getResourceFilePath("dictionaryTagger");
	 * dictionary =
	 * (DictionaryTagger)Class.forName(dictionaryName).newInstance();
	 * 
	 * //configure dictionary DictionaryTagger d = new DictionaryTagger();
	 * d.filterContainedMentions = (Boolean)
	 * aContext.getConfigParameterValue("filterContainedMentions");
	 * d.normalizeMixedCase = (Boolean)
	 * aContext.getConfigParameterValue("normalizeMixedCase"); d.normalizeDigits
	 * = (Boolean) aContext.getConfigParameterValue("normalizeDigits");
	 * d.generate2PartVariations = (Boolean)
	 * aContext.getConfigParameterValue("generate2PartVariations");
	 * d.dropEndParentheticals = (Boolean)
	 * aContext.getConfigParameterValue("dropEndParentheticals");
	 * 
	 * 
	 * 
	 * String dictionaryTypeName = (String)
	 * aContext.getConfigParameterValue("dictionaryType");
	 * 
	 * 
	 * 
	 * 
	 * String delimiter = (String)
	 * aContext.getConfigParameterValue("delimiter"); int column = (Integer)
	 * aContext.getConfigParameterValue("column"); EntityType dictionaryType =
	 * EntityType.getType(dictionaryTypeName);
	 * 
	 * // Load data BufferedReader reader = new BufferedReader(new
	 * FileReader(dictionaryName)); String line = reader.readLine(); while (line
	 * != null) { line = line.trim(); if (line.length() > 0) { if (delimiter ==
	 * null) { add(line, dictionaryType); } else { // TODO Performance - don't
	 * use split String[] split = line.split(delimiter); add(split[column],
	 * dictionaryType); } } line = reader.readLine(); } reader.close();
	 * 
	 * } catch (ResourceAccessException e) { e.printStackTrace(); } catch
	 * (InstantiationException e) { e.printStackTrace(); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (ClassNotFoundException e) { e.printStackTrace(); } return null; }
	 */

}
