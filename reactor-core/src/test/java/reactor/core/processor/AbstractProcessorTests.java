/*
 * Copyright (c) 2011-2015 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.core.processor;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.TestEnvironment;
import org.testng.annotations.AfterClass;
import reactor.Publishers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Stephane Maldini
 */
public abstract class AbstractProcessorTests extends org.reactivestreams.tck.IdentityProcessorVerification<Long> {

	final ExecutorService executorService = Executors.newCachedThreadPool();

	public AbstractProcessorTests() {
		super(new TestEnvironment(500, true), 1000);
	}

	@Override
	public ExecutorService publisherExecutorService() {
		return executorService;
	}

	@AfterClass
	public void tearDown(){
		executorService.shutdown();
	}

	@Override
	public Long createElement(int element) {
		return (long) element;
	}

	@Override
	public Publisher<Long> createFailedPublisher() {
		return Publishers.error(new Exception("test"));
	}
}
