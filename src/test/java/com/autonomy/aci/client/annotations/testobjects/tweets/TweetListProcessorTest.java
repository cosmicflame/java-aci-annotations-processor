/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.autonomy.aci.client.annotations.testobjects.tweets;

import com.autonomy.aci.client.annotations.IdolAnnotationsProcessorFactoryImpl;
import com.autonomy.aci.client.services.StAXProcessor;
import com.hp.autonomy.test.xml.XmlTestUtils;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TweetListProcessorTest {
    private IdolAnnotationsProcessorFactoryImpl reflectionProcessorFactory;

    @Before
    public void setUp() {
        reflectionProcessorFactory = new IdolAnnotationsProcessorFactoryImpl();
    }

    @Test
    public void testListLiteral() throws XMLStreamException {
        final XMLStreamReader xmlStreamReader = XmlTestUtils.getResourceAsXMLStreamReader("/com/autonomy/aci/client/annotations/tweets.xml");

        final StAXProcessor<List<Tweet>> processor = reflectionProcessorFactory.listProcessorForClass(Tweet.class);

        final List<Tweet> tweets = processor.process(xmlStreamReader);

        assertThat(tweets.size(), is(6));

        final Tweet tweet0 = tweets.get(0);

        assertThat(tweet0.getCleanTweet(), is(" Correction: The Supreme Court backs all parts of President Obama\u2019s health care law. "));
        assertThat(tweet0.getReference(), is("218348440483151873"));
        assertThat(tweet0.getPositivity(), is(closeTo(21.62, 0.005)));
    }
}
