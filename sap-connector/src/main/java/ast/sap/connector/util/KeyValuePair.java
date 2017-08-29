package ast.sap.connector.util;

public class KeyValuePair<K, V> {
	public final K key;
	public final V value;

	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "KeyValuePair [key=" + key + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		KeyValuePair other = (KeyValuePair) obj;
		if (key == null) {
			if (other.key != null) return false;
		} else if (!key.equals(other.key)) return false;
		if (value == null) {
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}
}
