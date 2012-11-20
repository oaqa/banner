
/* First created by JCasGen Fri Nov 18 16:09:58 CST 2011 */
package banner.types.uima;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** stores information about the source of the input
 * Updated by JCasGen Mon Nov 21 11:33:38 CST 2011
 * @generated */
public class Source_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Source_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Source_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Source(addr, Source_Type.this);
  			   Source_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Source(addr, Source_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Source.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("banner.types.uima.Source");
 
  /** @generated */
  final Feature casFeat_filePath;
  /** @generated */
  final int     casFeatCode_filePath;
  /** @generated */ 
  public String getFilePath(int addr) {
        if (featOkTst && casFeat_filePath == null)
      jcas.throwFeatMissing("filePath", "banner.types.uima.Source");
    return ll_cas.ll_getStringValue(addr, casFeatCode_filePath);
  }
  /** @generated */    
  public void setFilePath(int addr, String v) {
        if (featOkTst && casFeat_filePath == null)
      jcas.throwFeatMissing("filePath", "banner.types.uima.Source");
    ll_cas.ll_setStringValue(addr, casFeatCode_filePath, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "banner.types.uima.Source");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "banner.types.uima.Source");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Source_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_filePath = jcas.getRequiredFeatureDE(casType, "filePath", "uima.cas.String", featOkTst);
    casFeatCode_filePath  = (null == casFeat_filePath) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_filePath).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

  }
}



    