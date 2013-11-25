package org.springframework.context.marshaller.schema.nodes;

import java.util.List;

import org.springframework.context.marshaller.schema.NodeContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MapValue implements Value {

	public static final String ELEMENT = "map";

	public static final String ENTRY_ELEMENT = "entry";
	
	public static final String KEY_ELEMENT = "key";
	
	public static final String VALUE_ELEMENT = "value";

	private List<MapEntry> list;

	public MapValue(List<MapEntry> list) {
		super();
		this.list = list;
	}

	public List<MapEntry> getList() {
		return list;
	}

	public void setList(List<MapEntry> list) {
		this.list = list;
	}

	@Override
	public Node visit(NodeContext context) {

		Element element = context.getDocument().createElement(ELEMENT);
		for (MapEntry entry : getList()) {
			Element entryElement = context.getDocument().createElement(ENTRY_ELEMENT);
			element.appendChild(entryElement);
			
			Element keyElement = context.getDocument().createElement(KEY_ELEMENT);
			entryElement.appendChild(keyElement);
			
			Element valueElement = context.getDocument().createElement(VALUE_ELEMENT);
			entryElement.appendChild(valueElement);
			
			keyElement.appendChild(entry.getKey().visit(context));
			valueElement.appendChild(entry.getValue().visit(context));
		}

		return element;
	}

	public static class MapEntry {
		private Value key;
		private Value value;

		public MapEntry(Value key, Value value) {
			super();
			this.key = key;
			this.value = value;
		}

		public Value getKey() {
			return key;
		}

		public void setKey(Value key) {
			this.key = key;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

	}

}
