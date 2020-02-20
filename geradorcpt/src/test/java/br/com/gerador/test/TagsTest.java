package br.com.gerador.test;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import control.ControlTags;
import model.Tag;
import util.Configuracao;

public class TagsTest {

	public final static String CORRECT_TAG_WITH_ALL_PROPERTIES = "{{" + Configuracao.defaultTags
			+ " name=\"image_banner\" type=\"image\" size=40 needed=false}}";
	public final static String loren = "Mussum Ipsum, cacilds vidis litro abertis. Pra lÃ¡ , depois divoltis porris,"
			+ TagsTest.CORRECT_TAG_WITH_ALL_PROPERTIES
			+ " paradis. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. "
			+ "NÃ£o sou faixa preta cumpadi, sou preto inteiris, inteiris. Suco de cevadiss, Ã© um leite "
			+ "divinis, qui tem lupuliz, matis, aguis e fermentis.";
	
	public final static String lorenWithMultiTags = TagsTest.loren + TagsTest.CORRECT_TAG_WITH_ALL_PROPERTIES;

	@Test
	public void test_verify_only_default_tag() {
		ControlTags control = new ControlTags();
		List<Tag> tags = control.findTags(TagsTest.loren);
		assertThat(tags.size(), CoreMatchers.is(1));
	}
	
	@Test
	public void test_verify_only_default_tag_with_more_one() {
		ControlTags control = new ControlTags();
		List<Tag> tags = control.findTags(TagsTest.lorenWithMultiTags);
		assertThat(tags.size(), CoreMatchers.is(2));
	}

}
