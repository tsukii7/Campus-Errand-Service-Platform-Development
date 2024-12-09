// custom-tab-bar/index.js
const app = getApp()
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    "color": "#7A7E83",
    "selectedColor": "#D81E06",
    allList: [
      [
        {
          "pagePath": "/pages/ordering/ordering",
          "text": "下单",
          "iconPath": "https://i.postimg.cc/HLzny39T/home.png",
          "selectedIconPath": "https://i.postimg.cc/pXKFdf4L/home-selected.png",
          selected: "ordering"
        },
        {
          "pagePath": "/pages/ordersForUser/ordersForUser",
          "text": "订单",
          "iconPath": "https://i.postimg.cc/W3r27nMD/orders.png",
          "selectedIconPath": "https://i.postimg.cc/6Q4w90bH/orders-selected.png",
          selected: "ordersForUser"
        },
        {
          "pagePath": "/pages/user/user",
          "text": "主页",
          "iconPath": "https://i.postimg.cc/Yq2H4kyL/user.png",
          "selectedIconPath": "https://i.postimg.cc/ZnVXPBDB/user-selected.png",
          selected: "user"
        }
      ],
      [
        {
          "pagePath": "/pages/takingOrder/takingOrder",
          "text": "接单",
          "iconPath": "https://i.postimg.cc/HLzny39T/home.png",
          "selectedIconPath": "https://i.postimg.cc/pXKFdf4L/home-selected.png",
          selected: "takingOrder"
        },
        {
          "pagePath": "/pages/ordersForRider/ordersForRider",
          "text": "订单",
          "iconPath": "https://i.postimg.cc/W3r27nMD/orders.png",
          "selectedIconPath": "https://i.postimg.cc/6Q4w90bH/orders-selected.png",
          selected: "ordersForRider"
        },
        {
          "pagePath": "/pages/user/user",
          "text": "主页",
          "iconPath": "https://i.postimg.cc/Yq2H4kyL/user.png",
          "selectedIconPath": "https://i.postimg.cc/ZnVXPBDB/user-selected.png",
          selected: "user"
        }
      ]
    ],
    selectList: []
  },
  /**
   * 生命周期方法
   */
  attached() {
    this.setData({
      selectList: this.data.allList[app.globalData.user.role]
    })
  },

  /**
   * 组件的方法列表
   */
  methods: {
    switchTab(e) {
      // console.log(e.currentTarget.dataset);
      let path = e.currentTarget.dataset.path
      let selected = e.currentTarget.dataset.selected
      // console.log(e.currentTarget.dataset.selected);
      wx.switchTab({
        url: path,
      })
    }
  }
})
