#!/bin/bash
generate-post-data() {
    cat <<EOF
{
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "insert": {
                "object": {
                    "com.telecom.cep.AlertEvent": {
                        "_time": $time,
                        "neName": "$neName",
                        "serverSerial": $serverSerial,
                        "summary": "$summary",
                        "circuit": "$circuit",
                        "severity": $severity,
                        "ncFunction": "$ncFunction"
                    }
                },
                "out-identifier": "$out",
                "return-object": false
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
EOF
}
url="http://localhost:8080/kie-server/services/rest/server/containers/instances/Open-Ticket_1.0.0-SNAPSHOT"
user="kieserver:kieserver1!"
header="Content-Type: application/json"
curl ${url} \
--user ${user} \
--header "${header}" \
--data @- << EOF
{
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
EOF
echo ""
echo "----------------"
time=$(($(gdate +%s%N)/1000000))
neName="bdhlmbch"
serverSerial=173880500
summary="BRANDON HILLS REMOTE RECTIFIER FAIL MJR - C3E35"
circuit="RECT - power"
severity=4
ncFunction="INSERT"
generate-post-data
echo "- - - - - - - -"
curl ${url} \
--user ${user} \
--header "${header}" \
--data "$(generate-post-data)"
echo ""
echo "----------------"
sleep 1s
time=$(($(gdate +%s%N)/1000000))
neName="bdhlmbch"
serverSerial=1738805010
summary="BRANDON HILLS REMOTE AC FAIL MJ - C3E3"
circuit="AC - power"
severity=0
ncFunction="INSERT"
generate-post-data
echo "- - - - - - - -"
curl ${url} \
--user ${user} \
--header "${header}" \
--data "$(generate-post-data)"
echo ""
echo "----------------"
sleep 1s
time=$(($(gdate +%s%N)/1000000))
neName="bdhlmbch"
serverSerial=1738805010
summary="BRANDON HILLS REMOTE AC FAIL MJ - C3E3"
circuit="AC - power"
severity=0
ncFunction="DELETE"
generate-post-data
echo "- - - - - - - -"
curl ${url} \
--user ${user} \
--header "${header}" \
--data "$(generate-post-data)"
echo ""
echo "----------------"
sleep 7s
time=$(($(gdate +%s%N)/1000000))
neName="xyzyyz"
serverSerial=123456789
summary="XYZ UVW"
circuit="XYZ"
severity=0
ncFunction="INSERT"
generate-post-data
echo "- - - - - - - -"
curl ${url} \
--user ${user} \
--header "${header}" \
--data "$(generate-post-data)"
echo ""
echo "----------------"
sleep 2s
curl ${url} \
--user "kieserver:kieserver1!" \
--header "Content-Type: application/json" \
--data @- << EOF
{
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
                "return-object": true
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
EOF
echo ""
echo "----------------"
curl ${url} \
--user ${user} \
--header "${header}" \
--data @- << EOF
{
    "lookup": "telecom-realtime-ksession",
    "commands": [
        {
            "dispose": {}
        }
    ]
}
EOF
echo ""