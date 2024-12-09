// pages/adminLogin/adminLogin.js
const app = getApp()
const encryption = require('../../utils/encryption')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    eye_images: ["https://i.postimg.cc/vBh5D3p2/eye-hidden.png", "https://i.postimg.cc/cCJw3fWx/eye-show.png"],
    show: 0,
    password: "",
  },

  login: function() {
    let ep = encryption.sha1(this.data.password)
    wx.showLoading({
      title: '登录中...',
    })
    setTimeout(() => {
      wx.request({
        url: app.globalData.urlPort + '/api/administer/login',
        method: 'GET',
        data: {
          adminName: "admin",
          password: ep
        },
        success:(res) => {
          wx.hideLoading()
          if(res.data.code === "-1") {
            wx.showToast({
              title: '密码错误',
              icon: 'error',
              duration: 1000
            })
          } else {
            wx.navigateTo({
              url: '/pages/admin/admin',
            })
          }
        },
        fail:(res) => {
          wx.hideLoading()
          wx.showToast({
            title: '服务器繁忙',
            icon: 'error',
            duration: 1000
          })
        }
      })
    }, 1000)
  },

  switchPasswordDisplay: function() {
    this.setData({
      show: this.data.show ^ 1
    })
  },
  
  onInputPassword: function(e) {
    this.setData({
      password: e.detail.value
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