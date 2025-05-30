package de.gurkenlabs.litiengine.environment.tilemap.xml;

import de.gurkenlabs.litiengine.environment.tilemap.ICustomProperty;
import de.gurkenlabs.litiengine.util.ColorHelper;
import java.awt.Color;
import java.net.URL;
import java.util.Objects;

/**
 * The {@code CustomProperty} class is an implementation of the {@link ICustomProperty} interface.
 *
 * <p>This class provides functionality for managing custom properties with various data types,
 * including methods for setting and retrieving property values, handling property types, and supporting XML serialization and deserialization.
 */
public class CustomProperty implements ICustomProperty {

  private String type;
  private String value;
  private URL location;

  /**
   * Instantiates a new {@code CustomProperty} instance.
   *
   * <p>
   * The default type for a custom property is {@code string} if not explicitly specified.
   * </p>
   */
  public CustomProperty() {
    this.type = CustomPropertyType.STRING;
    this.value = "";
  }

  /**
   * Instantiates a new {@code CustomProperty} instance.
   *
   * @param value The value of this custom property.
   */
  public CustomProperty(String value) {
    this.type = CustomPropertyType.STRING;
    this.value = Objects.requireNonNull(value);
  }

  /**
   * Instantiates a new {@code CustomProperty} instance.
   *
   * @param type  The type of this custom property.
   * @param value The value of this custom property.
   */
  public CustomProperty(String type, String value) {
    this.type = Objects.requireNonNull(type);
    this.value = Objects.requireNonNull(value);
  }

  /**
   * Instantiates a new {@code CustomProperty} instance.
   *
   * @param location The location of the file represented by this custom property.
   */
  public CustomProperty(URL location) {
    this.type = CustomPropertyType.FILE;
    this.value = location.toExternalForm();
    this.location = location;
  }

  /**
   * Instantiates a new {@code CustomProperty} instance by copying from the specified instance.
   *
   * @param propertyToBeCopied The property to be copied.
   */
  public CustomProperty(ICustomProperty propertyToBeCopied) {
    this.type = propertyToBeCopied.getType();
    this.value = propertyToBeCopied.getAsString();
    this.location = propertyToBeCopied.getAsFile();
  }

  @Override
  public void setValue(URL location) {
    this.value = location.toExternalForm();
    this.location = location;
  }

  @Override
  public void setValue(String value) {
    this.value = Objects.requireNonNull(value);
    this.location = null;
  }

  @Override
  public void setValue(char value) {
    this.value = Character.toString(value);
    this.location = null;
  }

  @Override
  public void setValue(Enum<?> value) {
    this.value = value.name();
    this.location = null;
  }

  @Override
  public void setValue(long value) {
    this.value = Long.toString(value);
    this.location = null;
  }

  @Override
  public void setValue(double value) {
    this.value = Double.toString(value);
    this.location = null;
  }

  @Override
  public void setValue(boolean value) {
    this.value = Boolean.toString(value);
    this.location = null;
  }

  @Override
  public void setValue(Color value) {
    this.value = ColorHelper.encode(Objects.requireNonNull(value));
    this.location = null;
  }

  @Override
  public String getAsString() {
    return this.value;
  }

  @Override
  public char getAsChar() {
    return this.value == null || this.value.isBlank() ? (char) 0 : this.value.charAt(0);
  }

  @Override
  public boolean getAsBool() {
    return Boolean.parseBoolean(this.value);
  }

  @Override
  public Color getAsColor() {
    return ColorHelper.decode(this.value);
  }

  @Override
  public float getAsFloat() {
    return Float.parseFloat(this.value);
  }

  @Override
  public double getAsDouble() {
    return Double.parseDouble(this.value);
  }

  @Override
  public byte getAsByte() {
    return Byte.parseByte(this.value);
  }

  @Override
  public short getAsShort() {
    return Short.parseShort(this.value);
  }

  @Override
  public int getAsInt() {
    return Integer.parseInt(this.value);
  }

  @Override
  public long getAsLong() {
    return Long.parseLong(this.value);
  }

  @Override
  public <T extends Enum<T>> T getAsEnum(Class<T> enumType) {
    try {
      return Enum.valueOf(enumType, this.value);
    } catch (IllegalArgumentException _) {
      // try to ignore case to retrieve the enum value as a fallback
      for (T enumValue : enumType.getEnumConstants()) {
        if (enumValue.name().compareToIgnoreCase(this.value) == 0) {
          return enumValue;
        }
      }
    }

    return null;
  }

  @Override
  public URL getAsFile() {
    return this.location;
  }

  @Override
  public int getMapObjectId() {
    return Integer.parseInt(this.value);
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public void setType(String type) {
    this.type = Objects.requireNonNull(type);
  }

  @Override
  public boolean equals(Object anObject) {
    if (this == anObject) {
      return true;
    }
    if (!(anObject instanceof ICustomProperty other)) {
      return false;
    }
    return this.getType().equals(other.getType()) && this.getAsString().equals(other.getAsString());
  }

  @Override
  public int hashCode() {
    return this.getType().hashCode() * 31 + this.getAsString().hashCode();
  }

  @Override
  public String toString() {
    return this.getAsString() + " (" + this.getType() + ')';
  }
}
