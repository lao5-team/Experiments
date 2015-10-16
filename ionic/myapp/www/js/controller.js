

(function ($) {
  var myApp = angular.module("starter", []);
  angular.module("starter").factory("auth_service", ["$http", "$q", function ($http, $q){
    var _signin = function (user) {
            var deferred = $q.defer();
            var url = "https://dev.yikeer.com/app/login.yk";

// function(data){
//               alert(String(data)); // John//  2pm
//              });
            alert(user);
            $http.post(url, user).success(function (data, status, headers, config) {
                //alert(status);
                deferred.resolve({"status": status, "data": data});
            }).error(function (data, status, headers, config) {
                deferred.resolve({"status": status, "data": data});
            });
            return deferred.promise;
          };

   // 

                return {
            signin: _signin,

        };
  }]);

  angular.module("starter").controller("auth_controller", ["auth_service", "$scope", "$http", "$q", function (auth_service, $scope, $http, $q){


    $scope.signin = function () {
    // document.write("tastasdt");
    // alert("登录失败");
          var _user = {};
            _user.loginUsername = $scope.username;
            _user.loginPassword = $scope.password;  
    };

    var _signin = function (user) {
            auth_service.signin(user).
            then(function (res) {
              alert(res.status + "\n" + JSON.stringify(res.data));
                if(res.status == 200){
                    $('#signinModal').modal('hide');
                    toastr.success("登录成功");
                    $localStorage.token = res.data.token;
                    $localStorage.uid = res.data.uid;
                    $rootScope.$broadcast('userAuth:authUpdatedEvt', {});
                }else{
                    toastr.error("登录失败");
                }
            }, function (res) {
                $('#signinModal').modal('hide');
                toastr.warning("很抱歉您登录失败");
            }).then(function () {
                clearForm();
                $scope.form.$setPristine();
            });
      };

            var user1 = {};
      user1.loginUserName = "646469306@qq.com";
      alert(JSON.stringify(user1));
      user1.loginPassword = "123456789";
      user1.isMobile = "1";
      alert(JSON.stringify(user1));
      //_signin("loginUserName=" + encodeURIComponent("646469306@qq.com") + "&loginPassword=" + encodeURIComponent("123456789") + "&isMobile=" + "1");

      $http({
        method  : 'POST',
        url     : 'https://dev.yikeer.com/app/login.yk',
        data    : $.param(user1),  // pass in data as strings
        headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
      }).success(function(data) {
            console.log(data);
            $scope.token = data.resultappmark_for_app;
            alert(data.resultappmark_for_app);
            if (!data.success) {
                // if not successful, bind errors to error variables
                $scope.errorName = data.errors.name;
                $scope.errorSuperhero = data.errors.superheroAlias;
            } else {
                // if successful, bind success message to message
                $scope.message = data.message;


            }
        });

      self.location='index.html';

  }]);

  //     var user1 = {};
  //           user1.username = "999";
  //           user1.password = "999";

  // $auth_controller.signin(user1);



}(jQuery));

// function login($http, $params){
//   $http.post('http://tev.ht.com/app/login.yk', params)
// }

// function IndexCtrl($scope, $http) {
//   $http.get('/api/posts').
//     success(function(data, status, headers, config) {
//       $scope.posts = data.posts;
//     });
// }

// function AddPostCtrl($scope, $http, $location) {
//   $scope.form = {};
//   $scope.submitPost = function () {
//     $http.post('/api/post', $scope.form).
//       success(function(data) {
//         $location.path('/');
//       });
//   };
// }

// function ReadPostCtrl($scope, $http, $routeParams) {
//   $http.get('/api/post/' + $routeParams.id).
//     success(function(data) {
//       $scope.post = data.post;
//     });
// }

// function EditPostCtrl($scope, $http, $location, $routeParams) {
//   $scope.form = {};
//   $http.get('/api/post/' + $routeParams.id).
//     success(function(data) {
//       $scope.form = data.post;
//     });

//   $scope.editPost = function () {
//     $http.put('/api/post/' + $routeParams.id, $scope.form).
//       success(function(data) {
//         $location.url('/readPost/' + $routeParams.id);
//       });
//   };
// }

// function DeletePostCtrl($scope, $http, $location, $routeParams) {
//   $http.get('/api/post/' + $routeParams.id).
//     success(function(data) {
//       $scope.post = data.post;
//     });

//   $scope.deletePost = function () {
//     $http.delete('/api/post/' + $routeParams.id).
//       success(function(data) {
//         $location.url('/');
//       });
//   };

//   $scope.home = function () {
//     $location.url('/');
//   };
// }