package com.fpcms.common.util;

import java.nio.ByteBuffer;

public class ByteBufferHelper {
	public enum TypeKind {
	    /**
	     * The primitive type {@code boolean}.
	     */
	    BOOLEAN(1),

	    /**
	     * The primitive type {@code byte}.
	     */
	    BYTE(1),

	    /**
	     * The primitive type {@code short}.
	     */
	    SHORT(2),

	    /**
	     * The primitive type {@code int}.
	     */
	    INT(4),

	    /**
	     * The primitive type {@code long}.
	     */
	    LONG(8),

	    /**
	     * The primitive type {@code char}.
	     */
	    CHAR(2),

	    /**
	     * The primitive type {@code float}.
	     */
	    FLOAT(8),

	    /**
	     * The primitive type {@code double}.
	     */
	    DOUBLE(8),
	    
	    BYTE_ARRAY(-1),
	    CHAR_ARRAY(-1),
	    STRING(-1);
	    
	    private TypeKind(int length) {
	    	this.length = length;
	    }
	    
	    private int length;
		public int getLength() {
			return length;
		};
	}
	
	public static class Field {
		String name;
		TypeKind type;
		int length;
		
		public Field(TypeKind type) {
			this(type,type.length);
		}
		
		public Field(TypeKind type, int length) {
			super();
			this.type = type;
			this.length = length;
			assert length > 0;
		}

		int position;
		Object value;
		
		public void updateValue(ByteBuffer buf,Object value) {
			this.value = value;
			buf.position(position);
			if(type == TypeKind.INT) {
				buf.putInt((Integer)value);
			}else if(type == TypeKind.BYTE_ARRAY) {
				buf.put((byte[])value);
			}else {
				throw new RuntimeException("cannot update value by type:"+type);
			}
		}

		public Object getValue() {
			return value;
		}
		
	}
	
	public static Field writerPosition = new Field(TypeKind.INT);
	
	public static void read(ByteBuffer buf,Field[] fields) {
		int fieldPosition = 0;
		for(int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.position = fieldPosition;
			fieldPosition += f.length;
		}
		
		for(int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			Object value = readValue(buf, f);
			f.value = value;
		}
		
	}

	private static Object readValue(ByteBuffer buf, Field f) {
		if(f.type == TypeKind.INT) {
			int value = buf.getInt();
			return value;
		}else if(f.type == TypeKind.BYTE_ARRAY) {
			byte[] b = new byte[f.length];
			buf.get(b);
			return b;			
		}else if(f.type == TypeKind.STRING) {
			byte[] b = new byte[f.length];
			buf.get(b);
			return new String(b);
		}else {
			throw new RuntimeException("unsupport type:"+f.type);
		}
	}
	
}
