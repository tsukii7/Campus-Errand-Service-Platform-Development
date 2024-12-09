// pages/user/user.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {
    }
  },

  toModifyAddress() {
    wx.navigateTo({
      url: '/pages/modifyAddress/modifyAddress',
    })
  },

  toInformation() {
    wx.navigateTo({
      url: '/pages/information/information',
    })
  },

  logout() {
    this.setData({
      'user.id': -1,
      'user.avatar': 'https://i.postimg.cc/W35pk90h/default.png',
      'user.name': '昵称',
      'user.email': 'default@mail.sustech.edu.cn',
      'user.role': -1,
    })
    wx.redirectTo({
      url: '/pages/login/login',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.setData({
        user: app.globalData.user
    })
    if(app.globalData.user.role === 0){
      wx.request({
        url: app.globalData.urlPort + '/api/client/get',
        method: 'GET',
        data:{
            id: app.globalData.user.id
        },
        success:(res) =>{
          this.setData({
              'user.name': res.data.data.clientName,
              'user.avatar': res.data.data.avatarUrl
          })
          app.globalData.user = this.data.user
        }
      })
    } else {
      wx.request({
        url: app.globalData.urlPort + '/api/courier/get',
        method: 'GET',
        data: {
          id: app.globalData.user.id
        },
        success:(res) => {
          this.setData({
              'user.name': res.data.data.courierName,
              'user.avatar': res.data.data.avatarUrl
          })
          app.globalData.user = this.data.user
        }
      })
    }
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: "user"
      })
    }
    wx.setNavigationBarTitle({
      title: '个人主页',
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})