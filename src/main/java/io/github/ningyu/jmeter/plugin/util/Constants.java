/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.ningyu.jmeter.plugin.util;

/**
 * Constants
 */
public class Constants {

	//Registry Protocol
	public static final String REGISTRY_NONE = "none";
	public static final String REGISTRY_ZOOKEEPER = "zookeeper";
	public static final String REGISTRY_NACOS = "nacos";
	public static final String REGISTRY_MULTICAST = "multicast";
	public static final String REGISTRY_REDIS = "redis";
	public static final String REGISTRY_SIMPLE = "simple";

	//RPC Protocol
	public static final String RPC_PROTOCOL_DUBBO = "dubbo";
	public static final String RPC_PROTOCOL_RMI = "rmi";
	public static final String RPC_PROTOCOL_HESSIAN = "hessian";
	public static final String RPC_PROTOCOL_HTTP = "http";
	public static final String RPC_PROTOCOL_WEBSERVICE = "webservice";
	public static final String RPC_PROTOCOL_THRIFT = "thrift";
	public static final String RPC_PROTOCOL_MEMCACHED = "memcached";
	public static final String RPC_PROTOCOL_REDIS = "redis";

	public static final String ASYNC = "async";
	public static final String SYMBOL = "://";

	public static final int INT_DEFAULT = 0;
	public static final double DOUBLE_DEFAULT = 0.0d;
	public static final boolean BOOLEAN_DEFAULT = false;
	public static final char CHAR_DEFAULT = '\u0000';
	public static final float FLOAT_DEFAULT = 0.0f;
	public static final byte BYTE_DEFAULT = 0;
	public static final long LONG_DEFAULT = 0l;
	public static final short SHORT_DEFAULT = 0;
	public static final int[] INT_ARRAY_DEFAULT = null;
	public static final double[] DOUBLE_ARRAY_DEFAULT = null;
	public static final boolean[] BOOLEAN_ARRAY_DEFAULT = null;
	public static final char[] CHAT_ARRAY_DEFAULT = null;
	public static final float[] FLOAT_ARRAY_DEFAULT = null;
	public static final byte[] BYTE_ARRAY_DEFAULT = null;
	public static final long[] LONG_ARRAY_DEFAULT = null;
	public static final short[] SHORT_ARRAY_DEFAULT = null;

}
