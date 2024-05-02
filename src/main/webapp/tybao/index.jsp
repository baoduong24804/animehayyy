<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<style>
    #search {
        border: 0;
    }
    /* CSS để vô hiệu hóa và kích hoạt thẻ a */
        .disabled {
            background-color: #ccc; /* Màu nền xám */
            pointer-events: none; /* Ngăn chặn sự kiện click */
        }
        .page-item:focus{
        
            outline: none;
        
        }


</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="bg-black">
    <div class="container-fluid">
        <header class="bg-dark">
            <div class="row d-flex align-items-center p-2">
                <div class="col-sm-3">
                    <img src="https://animehay.video/themes/img/logo.png" alt="">
                </div>
                <div class="col-sm-6">
                    <div class="input-group">
                        <input type="text" class="form-control btn-outline-dark" placeholder="Film"
                            aria-label="Recipient's username" aria-describedby="search">
                        <span class="btn btn-outline-dark bg-white" id="search"><i
                                class="bi bi-search text-black p-0"></i></span>
                    </div>
                </div>
                <div class="col-sm-3 text-center">
                    <a class="bg-secondary p-2 m-2" href="#"><i class="bi bi-list text-black"></i></a>
                    <a class="bg-secondary p-2 m-2" href="#"><i class="bi bi-list text-black"></i></a>
                    <a class="bg-secondary p-2 m-2" href="#"><i class="bi bi-list text-black"></i></a>
                    <a class="bg-secondary p-2 m-2" href="#"><i class="bi bi-list text-black"></i></a>
                </div>
            </div>
        </header>
        <section class="bg-dark">
            <div class="alert alert-success" role="alert">
                A simple success alert—check it out!
            </div>
            <div class="row">
            <c:forEach items="${list }" var="item">
                            <div class="col-sm-2 mb-4">
                    <div class="card-body position-relative p-0">
                        <img src="${item.getThumb_url() }" class="img-fluid p-0" style="height: 300px; width: 100%;" alt="...">
                        <div class="position-absolute bottom-0 start-0 end-0 text-white p-1" style="background-color: rgba(0,0,0,0.5);">
                            <h5 class="card-title text-center mb-0 fs-6">${item.getName() }</h5>
                        </div>
                    </div>
                </div>
            </c:forEach>

                
            </div>
        </section>
        <footer class="m-3">
       


          <nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li id="previousLink" class="page-item disabled"><a class="page-link" href="/AnimeDom/home?page=previous" onclick="handlePreviousLink(event)">Previous</a></li>
    <li class="page-item"><a class="page-link ${param.page == 1 ? 'bg-secondary' : ''}" href="/AnimeDom/home?page=1">1</a></li>
    <li class="page-item"><a class="page-link ${param.page == 2 ? 'bg-secondary' : ''}" href="/AnimeDom/home?page=2">2</a></li>
    <li class="page-item"><a class="page-link ${param.page == 3 ? 'bg-secondary' : ''}" href="/AnimeDom/home?page=3">3</a></li>
    <li class="page-item"><a class="page-link ${param.page == 4 ? 'bg-secondary' : ''}" href="/AnimeDom/home?page=4">4</a></li>
    <li id="nextLink" class="page-item"><a class="page-link" href="/AnimeDom/home?page=next" onclick="handleNextLink(event)">Next</a></li>
  </ul>
</nav>




        </footer>
    </div>


<script>

	

        function handleNextLink(event) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
            var currentPage = parseInt(getParameterByName('page'));
            
            var nextPage = currentPage + 1;

            // Tạo đường dẫn mới với trang tiếp theo
            var newPageURL = "/AnimeDom/home?page=" + nextPage;
            
            // Chuyển hướng đến trang mới
            window.location.href = newPageURL;
        }
        
        document.addEventListener('DOMContentLoaded', function() {
            var currentPage = parseInt(getParameterByName('page'));
            if (currentPage <= 1) {
                document.getElementById('previousLink').classList.add('disabled');
                console.log('hello1');
            } else {
                document.getElementById('previousLink').classList.remove('disabled');
                console.log('hello');
            }
        });
        
        function handlePreviousLink(event) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
            var currentPage = parseInt(getParameterByName('page'));
            
            if (currentPage <= 1) {
                
                return;
            }
            var previousPage = currentPage - 1;

            // Tạo đường dẫn mới với trang tiếp theo
            var newPageURL = "/AnimeDom/home?page=" + previousPage;
            
            // Chuyển hướng đến trang mới
            window.location.href = newPageURL;
        }

        function getParameterByName(name) {
            var url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        }
    </script>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        
</body>

</html>