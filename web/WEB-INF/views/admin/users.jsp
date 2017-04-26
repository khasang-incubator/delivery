<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>

    <script type="text/javascript">
        var service = '/admin/users';
        function viewItem (table, item) {
            var tr = document.createElement('tr');

            var td = document.createElement('td');
            td.setAttribute('style', 'display:none');
            td.innerHTML = item.id;
            tr.appendChild(td);

            var mv = document.createElement('a');
            mv.setAttribute('href', service + "/get/id/" + item.id);
            mv.innerText = item.login;

            td = document.createElement('td');
            td.appendChild(mv);
            tr.appendChild(td);

            td = document.createElement('td');
            if (item.role=='ROLE_ADMIN'){
                td.innerHTML = 'Администратор';
            } else {
                td.innerHTML = item.role;
            }
            tr.appendChild(td);

            td = document.createElement('td');
            td.innerHTML = item.active;
            tr.appendChild(td);

            var del = document.createElement('button');
            del.setAttribute('type', "button");
            del.setAttribute('class', "btn btn-danger btn-xs");
            del.onclick = function(){
                var index = $(this).parent().parent().index();
                var val = $("#response").find("tr")[index].firstChild.innerText;
                if (confirm("Вы уверены?")){
                    RestDeleteById(val);
                }
            };
            del.innerText = 'Удалить';

            td = document.createElement('td');
            td.appendChild(del);
            tr.appendChild(td);

            table.appendChild(tr);
        }

        var parseResult = function (result) {
            var table = document.getElementById('response');
          
            while(table.firstChild){
                table.removeChild(table.firstChild);
            }

            if (result.length) {
                for (var i=0; i < result.length; i++) {
                    viewItem(table, result[i]);
                }
            } else {
                viewItem(table, result);
            }
        };

        var RestDeleteById = function (id) {
            $.ajax({
                type: 'DELETE',
                url: service + "/delete/" + id,
                dataType: 'json',
                async: false,
                success: RestGetAll,
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#response').html(JSON.stringify(jqXHR));
                }
            })
        };

        var RestGetAll = function (all) {
            $.ajax({
                type: 'GET',
                url: service + "/get/all",
                dataType: 'json',
                async: false,
                success: parseResult,
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#response').html(JSON.stringify(jqXHR));
                }
            })
        };

        window.onload=RestGetAll;
    </script>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading"><strong>Пользователи</strong></div>

    <table class="table table-hover table-condensed">
        <thead>
        <th>Логин</th>
        <th>Роль</th>
        <th>Активен</th>
        <th>#</th>
        </thead>
        <tbody id="response"></tbody>
    </table>
</div>
<a href="/admin/adduser" class="btn btn-default">
    <span class="glyphicon glyphicon-plus"></span>
    Новый пользователь
</a>
</body>
</html>
