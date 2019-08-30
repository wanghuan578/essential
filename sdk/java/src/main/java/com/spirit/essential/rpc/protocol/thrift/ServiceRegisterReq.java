/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.spirit.essential.rpc.protocol.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2019-08-22")
public class ServiceRegisterReq implements org.apache.thrift.TBase<ServiceRegisterReq, ServiceRegisterReq._Fields>, java.io.Serializable, Cloneable, Comparable<ServiceRegisterReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ServiceRegisterReq");

  private static final org.apache.thrift.protocol.TField ROUTE_FIELD_DESC = new org.apache.thrift.protocol.TField("route", org.apache.thrift.protocol.TType.STRUCT, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ServiceRegisterReqStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ServiceRegisterReqTupleSchemeFactory();

  public com.spirit.essential.rpc.protocol.thrift.RouteInfo route; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROUTE((short)1, "route");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

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
        case 1: // ROUTE
          return ROUTE;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROUTE, new org.apache.thrift.meta_data.FieldMetaData("route", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.spirit.essential.rpc.protocol.thrift.RouteInfo.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ServiceRegisterReq.class, metaDataMap);
  }

  public ServiceRegisterReq() {
  }

  public ServiceRegisterReq(
    com.spirit.essential.rpc.protocol.thrift.RouteInfo route)
  {
    this();
    this.route = route;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ServiceRegisterReq(ServiceRegisterReq other) {
    if (other.isSetRoute()) {
      this.route = new com.spirit.essential.rpc.protocol.thrift.RouteInfo(other.route);
    }
  }

  public ServiceRegisterReq deepCopy() {
    return new ServiceRegisterReq(this);
  }

  @Override
  public void clear() {
    this.route = null;
  }

  public com.spirit.essential.rpc.protocol.thrift.RouteInfo getRoute() {
    return this.route;
  }

  public ServiceRegisterReq setRoute(com.spirit.essential.rpc.protocol.thrift.RouteInfo route) {
    this.route = route;
    return this;
  }

  public void unsetRoute() {
    this.route = null;
  }

  /** Returns true if field route is set (has been assigned a value) and false otherwise */
  public boolean isSetRoute() {
    return this.route != null;
  }

  public void setRouteIsSet(boolean value) {
    if (!value) {
      this.route = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROUTE:
      if (value == null) {
        unsetRoute();
      } else {
        setRoute((com.spirit.essential.rpc.protocol.thrift.RouteInfo)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROUTE:
      return getRoute();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROUTE:
      return isSetRoute();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ServiceRegisterReq)
      return this.equals((ServiceRegisterReq)that);
    return false;
  }

  public boolean equals(ServiceRegisterReq that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_route = true && this.isSetRoute();
    boolean that_present_route = true && that.isSetRoute();
    if (this_present_route || that_present_route) {
      if (!(this_present_route && that_present_route))
        return false;
      if (!this.route.equals(that.route))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoute()) ? 131071 : 524287);
    if (isSetRoute())
      hashCode = hashCode * 8191 + route.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ServiceRegisterReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRoute()).compareTo(other.isSetRoute());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoute()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.route, other.route);
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
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ServiceRegisterReq(");
    boolean first = true;

    sb.append("route:");
    if (this.route == null) {
      sb.append("null");
    } else {
      sb.append(this.route);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (route != null) {
      route.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ServiceRegisterReqStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceRegisterReqStandardScheme getScheme() {
      return new ServiceRegisterReqStandardScheme();
    }
  }

  private static class ServiceRegisterReqStandardScheme extends org.apache.thrift.scheme.StandardScheme<ServiceRegisterReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ServiceRegisterReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROUTE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.route = new com.spirit.essential.rpc.protocol.thrift.RouteInfo();
              struct.route.read(iprot);
              struct.setRouteIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ServiceRegisterReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.route != null) {
        oprot.writeFieldBegin(ROUTE_FIELD_DESC);
        struct.route.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ServiceRegisterReqTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceRegisterReqTupleScheme getScheme() {
      return new ServiceRegisterReqTupleScheme();
    }
  }

  private static class ServiceRegisterReqTupleScheme extends org.apache.thrift.scheme.TupleScheme<ServiceRegisterReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ServiceRegisterReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoute()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetRoute()) {
        struct.route.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ServiceRegisterReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.route = new com.spirit.essential.rpc.protocol.thrift.RouteInfo();
        struct.route.read(iprot);
        struct.setRouteIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
