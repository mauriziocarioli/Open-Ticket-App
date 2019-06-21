import requests
import json
import time

current_milli_time = lambda: int(round(time.time()*1000))

url = 'http://localhost:8080/kie-server/services/rest/server'
authentication = ('kieserver','kieserver1!')
headers = {'Content-Type': 'application/xml'}
response = requests.get( url = url, headers = headers, auth = authentication )
print( response.content )

url = 'http://localhost:8080/kie-server/services/rest/server/containers/instances/Open-Ticket_1.0.0-SNAPSHOT'
authentication = ('kieserver','kieserver1!')
headers = {'Content-Type': 'application/json'}
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "set-global": {
                "identifier": "issues",
                "object": {
                    "com.telecom.Issues": {
                        "list": [], "lastIssueId": -1
                    }
                },
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "insert": {
                "object": {
                    "com.telecom.cep.AlertEvent": {
                        "_time": current_milli_time(),
                        "neName": "bdhlmbch",
                        "serverSerial": 173880500,
                        "summary": "BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35",
                        "circuit": "RECT - power",
                        "severity": 4,
                        "ncFunction": "INSERT"
                    }
                },
                "out-identifier": "rectifier-down",
                "return-object": "false"
            }
        },
        {
            "fire-all-rules": {}
        },
        {
            "get-global": {
                "identifier": "issues",
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
time.sleep(1)
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "insert": {
                "object": {
                    "com.telecom.cep.AlertEvent": {
                    	"_time": current_milli_time(),
                        "neName": "bdhlmbch",
                        "serverSerial": 1738805010,
                        "summary": "BRANDON HILLS REMOTE AC FAIL MJ - C3E3",
                        "circuit": "AC - power",
                        "severity": 0,
                        "ncFunction": "INSERT"
                    }
                },
                "out-identifier": "ac-down",
                "return-object": "false"
            }
        },
        {
            "fire-all-rules": {}
        },
        {
            "get-global": {
                "identifier": "issues",
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
time.sleep(1)
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "insert": {
                "object": {
                    "com.telecom.cep.AlertEvent": {
                    	"_time": current_milli_time(),
                        "neName": "bdhlmbch",
                        "serverSerial": 1738805010,
                        "summary": "BRANDON HILLS REMOTE AC FAIL MJ - C3E3",
                        "circuit": "AC - power",
                        "severity": 0,
                        "ncFunction": "DELETE"
                    }
                },
                "out-identifier": "ac-up",
                "return-object": "false"
            }
        },
        {
            "fire-all-rules": {}
        },
        {
            "get-global": {
                "identifier": "issues",
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
time.sleep(7)
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "fire-all-rules": {}
        },
        {
            "get-global": {
                "identifier": "issues",
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
time.sleep(2)
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "insert": {
                "object": {
                    "com.telecom.TicketCreated": {
                    	"issueId": 0
                    }
                },
                "out-identifier": "ticketIsCreated",
                "return-object": "true"
            }
        },
        {
            "fire-all-rules": {}
        },
        {
            "get-global": {
                "identifier": "issues",
                "out-identifier": "issues"
            }
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "dispose": {}
        }
    ]
}
response = requests.post( url = url, headers = headers, auth = authentication, data = json.dumps(body) )
print( response.content )