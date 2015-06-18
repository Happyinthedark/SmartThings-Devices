/*
** This device type can be used with any typical zwave thermostat with little, if any, modifications.  It was modeled after the default ST device type.
** If used with an Evolve T100R, just enable cycler mode on the thermo under advanced settings to allow ** the Circulate tile to work, otherwise 
** you'll just be able to only use Fan Auto and Fan On.
*/
metadata {
	// Automatically generated. Make future change here.
	definition (name: "My Thermostat v3 - 1st Floor", namespace: "jscgs350", author: "jscgs350")
    {
		capability "Refresh"
		capability "Actuator"
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
		capability "Thermostat"
		capability "Configuration"
		capability "Polling"
		capability "Sensor"

		command "heatLevelUp"
		command "heatLevelDown"
		command "coolLevelUp"
		command "coolLevelDown"
        command "quickSetCool"
        command "quickSetHeat"
        command "modeoff"
        command "modeheat"
        command "modecool"
        command "fanauto"
        command "fanon"
        command "fancir"
        
		attribute "thermostatFanState", "string"
        attribute "currentState", "string"
        attribute "currentMode", "string"
        attribute "currentfanMode", "string"
        
		fingerprint deviceId: "0x08"
		fingerprint inClusters: "0x43,0x40,0x44,0x31"
	}

//Thermostat Temp and State Row
	tiles
    {
		valueTile("temperature", "device.temperature", width: 2, height: 2) {
			state("temperature", label:'${currentValue}°', unit:"F",
				backgroundColors:[
					[value: 31, color: "#153591"],
					[value: 44, color: "#1e9cbb"],
					[value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 95, color: "#d04e00"],
					[value: 96, color: "#bc2323"]
				]
			)
		}
        standardTile("thermostatOperatingState", "device.currentState", canChangeIcon: false, inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
            state ("default", label:'${currentValue}', icon:"st.tesla.tesla-hvac")
        }
		standardTile("thermostatFanState", "device.thermostatFanState", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
            state "running", label:'Fan is On', icon:"st.Appliances.appliances11"
            state "idle", label:'Fan is Off', icon:"st.Appliances.appliances11"
        }        

//Thermostat Mode Control Row
        standardTile("modeoff", "device.thermostatMode", inactiveLabel: false, decoration: "flat") {
            state "off", label:'', action:"modeoff", icon:"st.thermostat.heating-cooling-off"
        }
        standardTile("modeheat", "device.thermostatMode", inactiveLabel: false, decoration: "flat") {
            state "heat", label:'', action:"modeheat", icon:"st.thermostat.heat"
        }
        standardTile("modecool", "device.thermostatMode", inactiveLabel: false, decoration: "flat") {
            state "cool", label:'', action:"modecool", icon:"st.thermostat.cool"
        }        

//Heating Set Point Controls Row
        standardTile("heatLevelUp", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state "heatLevelUp", label:'Heat', action:"heatLevelUp", icon:"st.thermostat.thermostat-up"//, backgroundColor:"#F7C4BA"
        }
        valueTile("heatingSetpoint", "device.heatingSetpoint", inactiveLabel: false) {
			state "heat", label:'${currentValue}°', unit:"F",
            	backgroundColors:[
					[value: 40, color: "#f49b88"],
					[value: 50, color: "#f28770"],
					[value: 60, color: "#f07358"],
					[value: 70, color: "#ee5f40"],
					[value: 80, color: "#ec4b28"],
					[value: 90, color: "#ea3811"]
				]
		}
		standardTile("heatLevelDown", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state "heatLevelDown", label:'Heat', action:"heatLevelDown", icon:"st.thermostat.thermostat-down"//, backgroundColor:"#F7C4BA"
        }

//Cooling Set Point Controls Row
        standardTile("coolLevelUp", "device.coolingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state "coolLevelUp", label:'Cool', action:"coolLevelUp", icon:"st.thermostat.thermostat-up"//, backgroundColor:"#F7C4BA"
        }
		valueTile("coolingSetpoint", "device.coolingSetpoint", inactiveLabel: false) {
			state "cool", label:'${currentValue}°', unit:"F",
            	backgroundColors:[
					[value: 40, color: "#88e1f4"],
					[value: 50, color: "#70dbf2"],
					[value: 60, color: "#58d5f0"],
					[value: 70, color: "#40cfee"],
					[value: 80, color: "#28c9ec"],
					[value: 90, color: "#11c3ea"]
				]
		}
		standardTile("coolLevelDown", "device.coolingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state "coolLevelDown", label:'Cool', action:"coolLevelDown", icon:"st.thermostat.thermostat-down"//, backgroundColor:"#F7C4BA"
        }

//Fan Mode Control Row        
        standardTile("fanauto", "device.thermostatFanMode", inactiveLabel: false, decoration: "flat") {
            state "fanauto", label:'', action:"fanauto", icon:"st.thermostat.fan-auto"
        }
        standardTile("fanon", "device.thermostatFanMode", inactiveLabel: false, decoration: "flat") {
            state "fanon", label:'', action:"fanon", icon:"st.thermostat.fan-on"
        }
        standardTile("fancir", "device.thermostatFanMode", inactiveLabel: false, decoration: "flat") {
            state "fancir", label:'', action:"fancir", icon:"st.thermostat.fan-circulate"
        }

//Refresh and Config Controls Row
        standardTile("modefan", "device.currentfanMode", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state ("default", label:'${currentValue}', icon:"st.Appliances.appliances11")
        }
		standardTile("refresh", "device.thermostatMode", inactiveLabel: false, decoration: "flat") {
			state "default", action:"poll", icon:"st.secondary.refresh"
		}
		standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat") {
			state "configure", label:'  ', action:"configuration.configure", icon:"st.secondary.configure"
		}

		main "temperature"
		
//        details(["temperature", "thermostatOperatingState", "thermostatFanState", "heatLevelDown", "heatingSetpoint", "heatLevelUp", "coolLevelDown", "coolingSetpoint", "coolLevelUp", "fanauto", "fanon", "fancir", "modeoff", "modeheat", "modecool", "modefan", "refresh", "configure"])
        details(["temperature", "thermostatOperatingState", "thermostatFanState", "heatLevelUp", "coolLevelUp", "fanauto", "heatingSetpoint", "coolingSetpoint", "fanon", "heatLevelDown", "coolLevelDown", "fancir", "modeoff", "modeheat", "modecool", "modefan", "refresh", "configure"])
	}
}

def parse(String description)
{
	def map = createEvent(zwaveEvent(zwave.parse(description, [0x42:1, 0x43:2, 0x31: 3])))
	if (!map) {
		return null
	}

	if (map.name == "thermostatFanMode"){
		if (map.value == "fanAuto") {
        	sendEvent(name: "currentfanMode", value: "Auto Mode" as String)
	    }
	    if (map.value == "fanOn") {
	        	sendEvent(name: "currentfanMode", value: "On Mode" as String)
		}
	    if (map.value == "fanCirculate") {
	        	sendEvent(name: "currentfanMode", value: "Cycle Mode" as String)
 	   	}
	}

	def result = [map]
	if (map.isStateChange && map.name in ["heatingSetpoint","coolingSetpoint","thermostatMode"]) {
		def map2 = [
			name: "thermostatSetpoint",
			unit: getTemperatureScale()
		]
		if (map.name == "thermostatMode") {
			state.lastTriedMode = map.value
			if (map.value == "cool") {
				map2.value = device.latestValue("coolingSetpoint")
				log.info "THERMOSTAT, latest cooling setpoint = ${map2.value}"
			}
			else {
				map2.value = device.latestValue("heatingSetpoint")
				log.info "THERMOSTAT, latest heating setpoint = ${map2.value}"
			}
		}
		else {
			def mode = device.latestValue("thermostatMode")
			log.info "THERMOSTAT, latest mode = ${mode}"
			if ((map.name == "heatingSetpoint" && mode == "heat") || (map.name == "coolingSetpoint" && mode == "cool")) {
				map2.value = map.value
				map2.unit = map.unit
			}
		}
		if (map2.value != null) {
			log.debug "THERMOSTAT, adding setpoint event: $map"
			result << createEvent(map2)
		}
	} else if (map.name == "thermostatFanMode" && map.isStateChange) {
		state.lastTriedFanMode = map.value
	}

	log.debug "Parse returned $result"
	result
}

//
//Receive updates from the thermostat and update the app
//

def zwaveEvent(physicalgraph.zwave.commands.thermostatsetpointv2.ThermostatSetpointReport cmd)
{
	def cmdScale = cmd.scale == 1 ? "F" : "C"
	def map = [:]
	map.value = convertTemperatureIfNeeded(cmd.scaledValue, cmdScale, cmd.precision)
	map.unit = getTemperatureScale()
	map.displayed = false
	switch (cmd.setpointType) {
		case 1:
			map.name = "heatingSetpoint"
			break;
		case 2:
			map.name = "coolingSetpoint"
			break;
		default:
			return [:]
	}
	// So we can respond with same format
	state.size = cmd.size
	state.scale = cmd.scale
	state.precision = cmd.precision
	map
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv3.SensorMultilevelReport cmd)
{
	def map = [:]
	if (cmd.sensorType == 1) {
		map.value = convertTemperatureIfNeeded(cmd.scaledSensorValue, cmd.scale == 1 ? "F" : "C", cmd.precision)
		map.unit = getTemperatureScale()
		map.name = "temperature"
	} else if (cmd.sensorType == 5) {
		map.value = cmd.scaledSensorValue
		map.unit = "%"
		map.name = "humidity"
	}
	map
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport cmd)
{
	def map = [:]
	switch (cmd.operatingState) {
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_IDLE:
			map.value = "idle"
            sendEvent(name: "currentState", value: "Idle" as String)
			def mode = device.latestValue("thermostatMode")
            if (mode == "off") {
				sendEvent(name: "currentState", value: "Unit is Off" as String)
			}
            if (mode == "aux") {
				sendEvent(name: "currentState", value: "AUX/EM is Idle" as String)
			}
            if (mode == "heat") {
				sendEvent(name: "currentState", value: "Heat is Idle" as String)
			}
            if (mode == "cool") {
				sendEvent(name: "currentState", value: "A/C is Idle" as String)
			}
            if (mode == "auto") {
				sendEvent(name: "currentState", value: "Auto is Idle" as String)
			}
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_HEATING:
			map.value = "heating"
            sendEvent(name: "currentState", value: "Heat is On" as String)
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_COOLING:
			map.value = "cooling"
            sendEvent(name: "currentState", value: "A/C is On" as String)
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_FAN_ONLY:
			map.value = "fan only"
            sendEvent(name: "currentState", value: "Fan Only" as String)
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_HEAT:
			map.value = "pending heat"
            sendEvent(name: "currentState", value: "Pending Heat" as String)
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_COOL:
			map.value = "pending cool"
            sendEvent(name: "currentState", value: "Pending A/C" as String)
			break
		case physicalgraph.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_VENT_ECONOMIZER:
			map.value = "vent economizer"
            sendEvent(name: "currentState", value: "Vent Eco" as String)
			break
	}
	map.name = "thermostatOperatingState"
	map
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanstatev1.ThermostatFanStateReport cmd) {
	def map = [name: "thermostatFanState", unit: ""]
	switch (cmd.fanOperatingState) {
		case 0:
			map.value = "idle"
			break
		case 1:
			map.value = "running"
			break
		case 2:
			map.value = "running high"
			break
	}
	map
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport cmd) {
	def map = [:]
	switch (cmd.mode) {
		case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_OFF:
			map.value = "off"
            sendEvent(name: "currentMode", value: "off" as String)
            break
		case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_HEAT:
			map.value = "heat"
            sendEvent(name: "currentMode", value: "heat" as String)
            break
		case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUXILIARY_HEAT:
			map.value = "emergencyHeat"
            sendEvent(name: "currentMode", value: "aux" as String)
            break
		case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_COOL:
			map.value = "cool"
            sendEvent(name: "currentMode", value: "cool" as String)
            def displayMode = map.value
            break
		case physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUTO:
			map.value = "auto"
            sendEvent(name: "currentMode", value: "auto" as String)
            break
	}
	map.name = "thermostatMode"
	map
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport cmd) {
	def map = [:]
	switch (cmd.fanMode) {
		case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_AUTO_LOW:
			map.value = "fanAuto"
            sendEvent(name: "currentfanMode", value: "Auto Mode" as String)
			break
		case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_LOW:
			map.value = "fanOn"
            sendEvent(name: "currentfanMode", value: "On Mode" as String)
			break
		case physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_CIRCULATION:
			map.value = "fanCirculate"
            sendEvent(name: "currentfanMode", value: "Cycle Mode" as String)
			break
	}
	map.name = "thermostatFanMode"
	map.displayed = false
	map
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatmodev2.ThermostatModeSupportedReport cmd) {
	def supportedModes = ""
	if(cmd.off) { supportedModes += "off " }
	if(cmd.heat) { supportedModes += "heat " }
	if(cmd.auxiliaryemergencyHeat) { supportedModes += "emergencyHeat " }
	if(cmd.cool) { supportedModes += "cool " }
	if(cmd.auto) { supportedModes += "auto " }
	state.supportedModes = supportedModes
}

def zwaveEvent(physicalgraph.zwave.commands.thermostatfanmodev3.ThermostatFanModeSupportedReport cmd) {
	def supportedFanModes = "fanAuto fanOn fanCirculate "
	state.supportedFanModes = supportedFanModes
}

def updateState(String name, String value) {
	state[name] = value
	device.updateDataValue(name, value)
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd) {
	log.debug "Zwave event received: $cmd"
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
	log.warn "Unexpected zwave command $cmd"
}

//
//Send commands to the thermostat
//

def heatLevelUp(){
    int nextLevel = device.currentValue("heatingSetpoint") + 1
    
    if( nextLevel > 90){
    	nextLevel = 90
    }
    log.debug "Setting heat set point up to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}

def heatLevelDown(){
    int nextLevel = device.currentValue("heatingSetpoint") - 1
    
    if( nextLevel < 40){
    	nextLevel = 40
    }
    log.debug "Setting heat set point down to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}

def quickSetHeat(degrees) {
	setHeatingSetpoint(degrees, 1000)
}

def setHeatingSetpoint(degrees, delay = 30000) {
	setHeatingSetpoint(degrees.toDouble(), delay)
}

def setHeatingSetpoint(Double degrees, Integer delay = 30000) {
	log.trace "setHeatingSetpoint($degrees, $delay)"
	def deviceScale = state.scale ?: 1
	def deviceScaleString = deviceScale == 2 ? "C" : "F"
    def locationScale = getTemperatureScale()
	def p = (state.precision == null) ? 1 : state.precision
    def convertedDegrees
    if (locationScale == "C" && deviceScaleString == "F") {
    	convertedDegrees = celsiusToFahrenheit(degrees)
    } else if (locationScale == "F" && deviceScaleString == "C") {
    	convertedDegrees = fahrenheitToCelsius(degrees)
    } else {
    	convertedDegrees = degrees
    }
	delayBetween([
		zwave.thermostatSetpointV1.thermostatSetpointSet(setpointType: 1, scale: deviceScale, precision: p, scaledValue: convertedDegrees).format(),
		zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 1).format()
	], delay)
}

def coolLevelUp(){
    int nextLevel = device.currentValue("coolingSetpoint") + 1
    
    if( nextLevel > 99){
    	nextLevel = 99
    }
    log.debug "Setting cool set point up to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def coolLevelDown(){
    int nextLevel = device.currentValue("coolingSetpoint") - 1
    
    if( nextLevel < 50){
    	nextLevel = 50
    }
    log.debug "Setting cool set point down to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def quickSetCool(degrees) {
	setCoolingSetpoint(degrees, 1000)
}

def setCoolingSetpoint(degrees, delay = 30000) {
	setCoolingSetpoint(degrees.toDouble(), delay)
}

def setCoolingSetpoint(Double degrees, Integer delay = 30000) {
    log.trace "setCoolingSetpoint($degrees, $delay)"
	def deviceScale = state.scale ?: 1
	def deviceScaleString = deviceScale == 2 ? "C" : "F"
    def locationScale = getTemperatureScale()
	def p = (state.precision == null) ? 1 : state.precision
    def convertedDegrees
    if (locationScale == "C" && deviceScaleString == "F") {
    	convertedDegrees = celsiusToFahrenheit(degrees)
    } else if (locationScale == "F" && deviceScaleString == "C") {
    	convertedDegrees = fahrenheitToCelsius(degrees)
    } else {
    	convertedDegrees = degrees
    }
	delayBetween([
		zwave.thermostatSetpointV1.thermostatSetpointSet(setpointType: 2, scale: deviceScale, precision: p,  scaledValue: convertedDegrees).format(),
		zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 2).format()
	], delay)
}

def modeoff() {
	log.debug "Setting thermostat mode to OFF."
	delayBetween([
		zwave.thermostatModeV2.thermostatModeSet(mode: 0).format(),
		zwave.thermostatModeV2.thermostatModeGet().format()
	], 1000)
}

def modeheat() {
	log.debug "Setting thermostat mode to HEAT."
	delayBetween([
		zwave.thermostatModeV2.thermostatModeSet(mode: 1).format(),
		zwave.thermostatModeV2.thermostatModeGet().format()
	], 1000)
}

def modecool() {
	log.debug "Setting thermostat mode to COOL."
	delayBetween([
		zwave.thermostatModeV2.thermostatModeSet(mode: 2).format(),
		zwave.thermostatModeV2.thermostatModeGet().format()
	], 1000)
}

def fanon() {
	delayBetween([
		zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 1).format(),
		zwave.thermostatFanModeV3.thermostatFanModeGet().format()
	], standardDelay)
}

def fanauto() {
	delayBetween([
		zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 0).format(),
		zwave.thermostatFanModeV3.thermostatFanModeGet().format()
	], standardDelay)
}

def fancir() {
	delayBetween([
		zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: 6).format(),
		zwave.thermostatFanModeV3.thermostatFanModeGet().format()
	], standardDelay)
}

def poll() {
	delayBetween([
		zwave.sensorMultilevelV3.sensorMultilevelGet().format(), // current temperature
		zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 1).format(),
		zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 2).format(),
		zwave.thermostatModeV2.thermostatModeGet().format(),
		zwave.thermostatFanStateV1.thermostatFanStateGet().format(),
		zwave.thermostatFanModeV3.thermostatFanModeGet().format(),
		zwave.thermostatOperatingStateV1.thermostatOperatingStateGet().format()
	], 2300)
}

def configure() {
	delayBetween([
		zwave.thermostatModeV2.thermostatModeSupportedGet().format(),
		zwave.thermostatFanModeV3.thermostatFanModeSupportedGet().format(),
		zwave.associationV1.associationSet(groupingIdentifier:1, nodeId:[zwaveHubNodeId]).format()
	], 2300)
}

private getStandardDelay() {
	1000
}
