angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope) {
  console.log("in DashCtrl");
})

.controller('FirstCtrl', function($scope, $cordovaBarcodeScanner) {
  $scope.scanStart = function () {

    $cordovaBarcodeScanner
      .scan()
      .then(function (barcodeData) {
        alert(barcodeData.text);
        $scope.barcodeData = barcodeData;
      }, function (error) {
        alert('失败');
      });
  };
})

.controller('OtherCtrl', ['$scope','$state', '$ionicViewSwitcher', function($scope, $state, $ionicViewSwitcher) {
  console.log("It runs in OtherCtrl....");
  // $state.go('other', {});
  // $ionicViewSwitcher.nextDirection("forward");
}])

.controller('ChatsCtrl', function($scope, Chats) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  $scope.chats = Chats.all();
  $scope.remove = function(chat) {
    Chats.remove(chat);
  };
})

.controller('ChatDetailCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
})

.controller('AccountCtrl', function($scope) {
  $scope.settings = {
    enableFriends: true
  };
});
