/**
 * 
 */
package com.telecom.cep;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Role.Type;
import org.kie.api.definition.type.Timestamp;

@Role(Type.EVENT)
@Timestamp("_time")
public class AlertEvent {

	private Long aId;
	private Boolean aknowledged;
	private String additionalInfo;
	private String agent;
	private Long aggregationFirst;
	private String alertGroup;
	private String alertKey;
	private String clliBuilding;
	private String clliCity;
	private String clliDcode;
	private String clliEntity;
	private String clliPro;
	private String circuit;
	private String circuitClass;
	private Long clearTime;
	private Long collectionFirst;
	private String comments;
	private String commonName;
	private Long displayFirst;
	private Long expireTime;
	private Long firstOccurrence;
	private String identifier;
	private Long lastOccurrence;
	private String location;
	private Boolean maintenance;
	private String manager;
	private Long masterSerial;
	private String neName;
	private Integer noc;
	private Integer network;
	private String networkName;
	private String nocName;
	private String node;
	private String nodeAlias;
	private Long oId;
	private String origSeverity;
	private Long ownerGid;
	private String ownerGroup;
	private String ownerName;
	private Long ownerUid;
	private Long serial;
	private String serverName;
	private Long serverSerial;
	private Integer serviceNowErrorCode;
	private Integer serviceNowState;
	private String serviceNowStateName;
	private Long serviceNowSysId;
	private Integer severity;
	private String severityName;
	private Long stateCharge;
	private String summary;
	private String ttNumber;
	private Integer tally;
	private String ticketLabel;
	private Integer timeToDisplay;
	private String troubleTicket;
	private Integer type;
	private String typeName;
	private String url;
	private String _raw;
	private Long _time;
	private String eventId;
	private String eventType;
	private String host;
	private String index;
	private Integer lineCount;
	private String ncFunction;
	private String ncSource;
	private String punct;
	private String source;
	private String sourceType;
	private String splunkServer;
	private String splunkServerGroup;
	private String tag;
	private String tagEventType;
	
	public Long getaId() {
		return aId;
	}
	public void setaId(Long aId) {
		this.aId = aId;
	}
	public Boolean getAknowledged() {
		return aknowledged;
	}
	public void setAknowledged(Boolean aknowledged) {
		this.aknowledged = aknowledged;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Long getAggregationFirst() {
		return aggregationFirst;
	}
	public void setAggregationFirst(Long aggregationFirst) {
		this.aggregationFirst = aggregationFirst;
	}
	public String getAlertGroup() {
		return alertGroup;
	}
	public void setAlertGroup(String alertGroup) {
		this.alertGroup = alertGroup;
	}
	public String getAlertKey() {
		return alertKey;
	}
	public void setAlertKey(String alertKey) {
		this.alertKey = alertKey;
	}
	public String getClliBuilding() {
		return clliBuilding;
	}
	public void setClliBuilding(String clliBuilding) {
		this.clliBuilding = clliBuilding;
	}
	public String getClliCity() {
		return clliCity;
	}
	public void setClliCity(String clliCity) {
		this.clliCity = clliCity;
	}
	public String getClliDcode() {
		return clliDcode;
	}
	public void setClliDcode(String clliDcode) {
		this.clliDcode = clliDcode;
	}
	public String getClliEntity() {
		return clliEntity;
	}
	public void setClliEntity(String clliEntity) {
		this.clliEntity = clliEntity;
	}
	public String getClliPro() {
		return clliPro;
	}
	public void setClliPro(String clliPro) {
		this.clliPro = clliPro;
	}
	public String getCircuit() {
		return circuit;
	}
	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}
	public String getCircuitClass() {
		return circuitClass;
	}
	public void setCircuitClass(String circuitClass) {
		this.circuitClass = circuitClass;
	}
	public Long getClearTime() {
		return clearTime;
	}
	public void setClearTime(Long clearTime) {
		this.clearTime = clearTime;
	}
	public Long getCollectionFirst() {
		return collectionFirst;
	}
	public void setCollectionFirst(Long collectionFirst) {
		this.collectionFirst = collectionFirst;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public Long getDisplayFirst() {
		return displayFirst;
	}
	public void setDisplayFirst(Long displayFirst) {
		this.displayFirst = displayFirst;
	}
	public Long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public Long getFirstOccurrence() {
		return firstOccurrence;
	}
	public void setFirstOccurrence(Long firstOccurrence) {
		this.firstOccurrence = firstOccurrence;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Long getLastOccurrence() {
		return lastOccurrence;
	}
	public void setLastOccurrence(Long lastOccurrence) {
		this.lastOccurrence = lastOccurrence;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(Boolean maintenance) {
		this.maintenance = maintenance;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Long getMasterSerial() {
		return masterSerial;
	}
	public void setMasterSerial(Long masterSerial) {
		this.masterSerial = masterSerial;
	}
	public String getNeName() {
		return neName;
	}
	public void setNeName(String neName) {
		this.neName = neName;
	}
	public Integer getNoc() {
		return noc;
	}
	public void setNoc(Integer noc) {
		this.noc = noc;
	}
	public Integer getNetwork() {
		return network;
	}
	public void setNetwork(Integer network) {
		this.network = network;
	}
	public String getNetworkName() {
		return networkName;
	}
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	public String getNocName() {
		return nocName;
	}
	public void setNocName(String nocName) {
		this.nocName = nocName;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getNodeAlias() {
		return nodeAlias;
	}
	public void setNodeAlias(String nodeAlias) {
		this.nodeAlias = nodeAlias;
	}
	public Long getoId() {
		return oId;
	}
	public void setoId(Long oId) {
		this.oId = oId;
	}
	public String getOrigSeverity() {
		return origSeverity;
	}
	public void setOrigSeverity(String origSeverity) {
		this.origSeverity = origSeverity;
	}
	public Long getOwnerGid() {
		return ownerGid;
	}
	public void setOwnerGid(Long ownerGid) {
		this.ownerGid = ownerGid;
	}
	public String getOwnerGroup() {
		return ownerGroup;
	}
	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Long getOwnerUid() {
		return ownerUid;
	}
	public void setOwnerUid(Long ownerUid) {
		this.ownerUid = ownerUid;
	}
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public Long getServerSerial() {
		return serverSerial;
	}
	public void setServerSerial(Long serverSerial) {
		this.serverSerial = serverSerial;
	}
	public Integer getServiceNowErrorCode() {
		return serviceNowErrorCode;
	}
	public void setServiceNowErrorCode(Integer serviceNowErrorCode) {
		this.serviceNowErrorCode = serviceNowErrorCode;
	}
	public Integer getServiceNowState() {
		return serviceNowState;
	}
	public void setServiceNowState(Integer serviceNowState) {
		this.serviceNowState = serviceNowState;
	}
	public String getServiceNowStateName() {
		return serviceNowStateName;
	}
	public void setServiceNowStateName(String serviceNowStateName) {
		this.serviceNowStateName = serviceNowStateName;
	}
	public Long getServiceNowSysId() {
		return serviceNowSysId;
	}
	public void setServiceNowSysId(Long serviceNowSysId) {
		this.serviceNowSysId = serviceNowSysId;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public String getSeverityName() {
		return severityName;
	}
	public void setSeverityName(String severityName) {
		this.severityName = severityName;
	}
	public Long getStateCharge() {
		return stateCharge;
	}
	public void setStateCharge(Long stateCharge) {
		this.stateCharge = stateCharge;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTtNumber() {
		return ttNumber;
	}
	public void setTtNumber(String ttNumber) {
		this.ttNumber = ttNumber;
	}
	public Integer getTally() {
		return tally;
	}
	public void setTally(Integer tally) {
		this.tally = tally;
	}
	public String getTicketLabel() {
		return ticketLabel;
	}
	public void setTicketLabel(String ticketLabel) {
		this.ticketLabel = ticketLabel;
	}
	public Integer getTimeToDisplay() {
		return timeToDisplay;
	}
	public void setTimeToDisplay(Integer timeToDisplay) {
		this.timeToDisplay = timeToDisplay;
	}
	public String getTroubleTicket() {
		return troubleTicket;
	}
	public void setTroubleTicket(String troubleTicket) {
		this.troubleTicket = troubleTicket;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String get_raw() {
		return _raw;
	}
	public void set_raw(String _raw) {
		this._raw = _raw;
	}
	public Long get_time() {
		return _time;
	}
	public void set_time(Long _time) {
		this._time = _time;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Integer getLineCount() {
		return lineCount;
	}
	public void setLineCount(Integer lineCount) {
		this.lineCount = lineCount;
	}
	public String getNcFunction() {
		return ncFunction;
	}
	public void setNcFunction(String ncFunction) {
		this.ncFunction = ncFunction;
	}
	public String getNcSource() {
		return ncSource;
	}
	public void setNcSource(String ncSource) {
		this.ncSource = ncSource;
	}
	public String getPunct() {
		return punct;
	}
	public void setPunct(String punct) {
		this.punct = punct;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSplunkServer() {
		return splunkServer;
	}
	public void setSplunkServer(String splunkServer) {
		this.splunkServer = splunkServer;
	}
	public String getSplunkServerGroup() {
		return splunkServerGroup;
	}
	public void setSplunkServerGroup(String splunkServerGroup) {
		this.splunkServerGroup = splunkServerGroup;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTagEventType() {
		return tagEventType;
	}
	public void setTagEventType(String tagEventType) {
		this.tagEventType = tagEventType;
	}

}
