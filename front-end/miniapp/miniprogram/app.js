// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    urlPort: 'http://localhost:8081',
    user: {
      id: -1,
      avatar: 'https://i.postimg.cc/W35pk90h/default.png',
      name: '昵称',
      email: 'default@mail.sustech.edu.cn',
      role: -1,
    },
    deliveryAddress: [{ address: "湖畔三栋", phoneNumber:'13011112222' }],
    orders: [
      {
        id: 0,
        type: 0, //0-外卖，1-快递，2-闪送
        startAddress: '六号门',
        endAddress: '湖畔三栋',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 0, //0-待接单（配送中），1-待收货（已送达），2-已完成，3-已取消
          rider: {},
        },
        info: '萨莉亚黑椒牛柳意面',
        price: 4.5,
        createdTime: '04/10 20:00',
        placedTime: '04/10 20:10',
        transitedTime: '',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '',
      },
      {
        id: 1,
        type: 1,
        startAddress: '湖畔五栋',
        endAddress: '湖畔二栋',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 1,
          rider: {
            name: '骑手',
            avatar: 'https://i.postimg.cc/ZnbqHQ4f/1.jpg',
          },
        },
        info: '123456',
        price: 5,
        createdTime: '04/12 19:40',
        placedTime: '04/12 19:50',
        transitedTime: '04/12 19:42',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '',
      },
      {
        id: 2,
        type: 2,
        startAddress: 'D',
        endAddress: 'E',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 2,
          rider: {
            name: '骑手',
            avatar: 'https://i.postimg.cc/ZnbqHQ4f/1.jpg',
          },
        },
        info: '123456',
        price: 5,
        createdTime: '04/12 19:40',
        placedTime: '04/12 19:50',
        transitedTime: '04/12 19:42',
        arrivedTime: '04/12 19:48',
        confirmedTime: '',
        cancelledTime: '',
      },
      {
        id: 3,
        type: 0,
        startAddress: 'D',
        endAddress: 'E',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 3,
          rider: {},
        },
        info: '123456',
        price: 6,
        createdTime: '04/12 21:00',
        placedTime: '40/12 21:10',
        transitedTime: '',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '04/12 21:10',
      },
    ],
    ordersToTake: [
      {
        id: 0,
        type: 0, //0-外卖，1-快递，2-闪送
        startAddress: '六号门',
        endAddress: '湖畔三栋',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 0, //0-待接单（配送中），1-待收货（已送达），2-已完成，3-已取消
          rider: {},
        },
        info: '萨莉亚黑椒牛柳意面',
        price: 4.5,
        createdTime: '04/10 20:00',
        placedTime: '04/10 20:10',
        transitedTime: '',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '',
      },
      {
        id: 1,
        type: 1, //0-外卖，1-快递，2-闪送
        startAddress: '六号门',
        endAddress: '湖畔三栋',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/RV0Fn2Ph/3.jpg'
        },
        status: {
          statusNumber: 0, //0-待接单（配送中），1-待收货（已送达），2-已完成，3-已取消
          rider: {},
        },
        info: '电脑配件',
        price: 4.5,
        createdTime: '04/10 20:00',
        placedTime: '04/10 20:10',
        transitedTime: '',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '',
      },
      {
        id: 2,
        type: 0, //0-外卖，1-快递，2-闪送
        startAddress: '六号门',
        endAddress: '湖畔三栋',
        owner: {
          name: 'aaa',
          phoneNumber: '13011112222',
          avatar: 'https://i.postimg.cc/mgzgmCWG/4.jpg'
        },
        status: {
          statusNumber: 0, //0-待接单（配送中），1-待收货（已送达），2-已完成，3-已取消
          rider: {},
        },
        info: '萨莉亚黑椒牛柳意面',
        price: 3.5,
        createdTime: '04/10 20:00',
        placedTime: '04/10 20:10',
        transitedTime: '',
        arrivedTime: '',
        confirmedTime: '',
        cancelledTime: '',
      },
    ]
  }
})
