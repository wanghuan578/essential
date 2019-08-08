package com.spirit.essential.thrift.idl;
/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2019-08-07")
public class HelloReq implements org.apache.thrift.TBase<HelloReq, HelloReq._Fields>, java.io.Serializable, Cloneable, Comparable<HelloReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HelloReq");

  private static final org.apache.thrift.protocol.TField SERVICE_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("service_info", org.apache.thrift.protocol.TType.STRUCT, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new HelloReqStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new HelloReqTupleSchemeFactory();

  public ServiceInfo service_info; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SERVICE_INFO((short)1, "service_info");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SERVICE_INFO
          return SERVICE_INFO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SERVICE_INFO, new org.apache.thrift.meta_data.FieldMetaData("service_info", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ServiceInfo.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HelloReq.class, metaDataMap);
  }

  public HelloReq() {
  }

  public HelloReq(
    ServiceInfo service_info)
  {
    this();
    this.service_info = service_info;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HelloReq(HelloReq other) {
    if (other.isSetService_info()) {
      this.service_info = new ServiceInfo(other.service_info);
    }
  }

  public HelloReq deepCopy() {
    return new HelloReq(this);
  }

  @Override
  public void clear() {
    this.service_info = null;
  }

  public ServiceInfo getService_info() {
    return this.service_info;
  }

  public HelloReq setService_info(ServiceInfo service_info) {
    this.service_info = service_info;
    return this;
  }

  public void unsetService_info() {
    this.service_info = null;
  }

  /** Returns true if field service_info is set (has been assigned a value) and false otherwise */
  public boolean isSetService_info() {
    return this.service_info != null;
  }

  public void setService_infoIsSet(boolean value) {
    if (!value) {
      this.service_info = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SERVICE_INFO:
      if (value == null) {
        unsetService_info();
      } else {
        setService_info((ServiceInfo)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SERVICE_INFO:
      return getService_info();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SERVICE_INFO:
      return isSetService_info();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HelloReq)
      return this.equals((HelloReq)that);
    return false;
  }

  public boolean equals(HelloReq that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_service_info = true && this.isSetService_info();
    boolean that_present_service_info = true && that.isSetService_info();
    if (this_present_service_info || that_present_service_info) {
      if (!(this_present_service_info && that_present_service_info))
        return false;
      if (!this.service_info.equals(that.service_info))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetService_info()) ? 131071 : 524287);
    if (isSetService_info())
      hashCode = hashCode * 8191 + service_info.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(HelloReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetService_info()).compareTo(other.isSetService_info());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetService_info()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.service_info, other.service_info);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("HelloReq(");
    boolean first = true;

    sb.append("service_info:");
    if (this.service_info == null) {
      sb.append("null");
    } else {
      sb.append(this.service_info);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (service_info != null) {
      service_info.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class HelloReqStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HelloReqStandardScheme getScheme() {
      return new HelloReqStandardScheme();
    }
  }

  private static class HelloReqStandardScheme extends org.apache.thrift.scheme.StandardScheme<HelloReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HelloReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SERVICE_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.service_info = new ServiceInfo();
              struct.service_info.read(iprot);
              struct.setService_infoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, HelloReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.service_info != null) {
        oprot.writeFieldBegin(SERVICE_INFO_FIELD_DESC);
        struct.service_info.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HelloReqTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HelloReqTupleScheme getScheme() {
      return new HelloReqTupleScheme();
    }
  }

  private static class HelloReqTupleScheme extends org.apache.thrift.scheme.TupleScheme<HelloReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HelloReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetService_info()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetService_info()) {
        struct.service_info.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HelloReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.service_info = new ServiceInfo();
        struct.service_info.read(iprot);
        struct.setService_infoIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

