package sj224.tflp.util;

import java.util.Map;

public interface BijectiveMap<K,V>extends Map<K,V>{
	BijectiveMap<V,K>inverse();
	K getKey(Object value);
}
