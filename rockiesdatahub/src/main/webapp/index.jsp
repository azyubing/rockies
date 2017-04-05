<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Spring4 RESTful</title>
    <SCRIPT TYPE="text/javascript">
            function go(value){
                var url = "/rockiesdatahub/simple/"+value+"/";
                var request =  new XMLHttpRequest();
                request.open("GET", url, true);
                request.setRequestHeader("Content-Type","application/x-javascript;");
                request.onreadystatechange = function() {
                    if (request.readyState == 4) {
                        if (request.status == 200){
                            if (request.responseText) {
                                document.getElementById("text").innerHTML = request.responseText;
                            }
                        }
                    }
                };
                request.send(null);
            }
        </SCRIPT>
  </head>
  
  <body>
    ${message}
    <br>
    Input the id of you will access object:<input id="id" type="text" size="7"><input type="button" value="Go" onclick="go(document.getElementById('id').value)">
  	<div id="text"></div>
  </body>
</html>
