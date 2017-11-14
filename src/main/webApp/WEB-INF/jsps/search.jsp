<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <script>

        function getData() {
            var request = new XMLHttpRequest();

            request.open('POST', 'employeesSearch.json');
            request.send();

            request.onreadystatechange = function() {
                if( request.readyState === 4 && request.status == 200) {

                    var table = document.getElementById('employeesSearch');
                    var jsonArr = JSON.parse(request.responseText);

                    for (var i = 0; i < jsonArr.length; i++) {
                        var row = table.insertRow(i);

                        var cellName = row.insertCell(0);
                        var cellSurname = row.insertCell(1);
                        var cellCity = row.insertCell(2);
                        var cellSalary = row.insertCell(3);
                        var cellPosition = row.insertCell(4);

                        cellName.innerHTML = jsonArr[i].name;
                        cellSurname.innerHTML = jsonArr[i].surname;
                        cellCity.innerHTML = jsonArr[i].city;
                        cellSalary.innerHTML = jsonArr[i].salary;
                        cellPosition.innerHTML = jsonArr[i].position;
                    }
                }
            }
        }
    </script>

</head>
<body onload="return getData()">

<h1>Search employees</h1>
<div id="div1">
    <table>
        <tr>
            <th style="width: 100px" align="left">Name</th>
            <th style="width: 130px" align="left">Surname</th>
            <th style="width: 160px" align="left">City</th>
            <th style="width: 80px"  align="left">Salary</th>
            <th style="width: 250px" align="left">Position</th>
        </tr>
    </table>
</div>

<div id="div2">
    <table id="employeesSearch">
        <tr>
            <td style="width: 100px"></td>
            <td style="width: 130px"></td>
            <td style="width: 160px"></td>
            <td style="width: 80px"></td>
            <td style="width: 250px"></td>
        </tr>
    </table>
</div>

<div>
    <h4>Back to
        <a href="/">index page</a>
    </h4>
    <h4>
        <a href="/userPage">employeesTable</a>
    </h4>
</div>

</body>
</html>