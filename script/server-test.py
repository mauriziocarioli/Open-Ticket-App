import requests
import json
import time

def current_milli_time(): return int(round(time.time()*1000))

def insert_alert(time,nename,serverserial,summary,circuit,severity,ncfunction):
    "insert an alert in working memory"
    body = {
        "lookup": "telecom-realtime-ksession",
        "commands": [
            {
                "insert": {
                    "object": {
                        "com.telecom.cep.AlertEvent": {
                            "_time": time,
                            "neName": nename,
                            "serverSerial": serverserial,
                            "summary": summary,
                            "circuit": circuit,
                            "severity": severity,
                            "ncFunction": ncfunction
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
    return body

url = 'http://localhost:8080/kie-server/services/rest/server/containers/instances/Open-Ticket_1.0.0-SNAPSHOT'
authentication = ('kieserver', 'kieserver1!')
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
print("===========> inserting empty issue list")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
body = insert_alert(current_milli_time(),
                    "bdhlmbch",
                    173880500,
                    "BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35",
                    "RECT - power",4,"INSERT")
print("===========> inserting alert")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
time.sleep(1)
body = insert_alert(current_milli_time(),
                    "bdhlmbch",
                    1738805010,
                    "BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35",
                    "AC - power",0,"INSERT")
print("===========> inserting alert")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
time.sleep(1)
body = insert_alert(current_milli_time(),
                    "bdhlmbch",
                    1738805010,
                    "BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35",
                    "AC - power",0,"DELETE")
print("===========> inserting alert and wait")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
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
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
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
print("===========> inserting 'ticket is created' fact")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))
body = {
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "dispose": {}
        }
    ]
}
print("===========> reset")
response = requests.post(url=url, headers=headers,
                         auth=authentication, data=json.dumps(body))
print(json.dumps(json.loads(response.content), indent=3))