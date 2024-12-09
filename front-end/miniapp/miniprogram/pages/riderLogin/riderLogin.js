// pages/riderLogin/riderLogin.js
const app = getApp()
const encryption = require('../../utils/encryption')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    eye_images: ["https://i.postimg.cc/vBh5D3p2/eye-hidden.png", "https://i.postimg.cc/cCJw3fWx/eye-show.png"],
    show: 0,
    user: {},
    mailbox: "",
    password: "",
  },

  switchPasswordDisplay: function() {
    this.setData({
      show: this.data.show ^ 1
    })
  },

  login: function() {
    let ep = encryption.sha1(this.data.password)
    wx.showLoading({
      title: '登录中...',
    })
    setTimeout(() => {
      wx.request({
        url: app.globalData.urlPort + '/api/courier/login',
        method: 'GET',
        data:{
          email: this.data.mailbox,
          password: ep
        },
        success:(res) => {
          wx.hideLoading()
          if(res.data.code != -1){
            this.setData({
              'user.id': res.data.data.id,
              'user.avatar': res.data.data.avatarUrl,
              'user.name': res.data.data.courierName,
              'user.email': res.data.data.email,
              'user.role': 1
            })
            wx.request({
              url: app.globalData.urlPort + '/api/courier/check-block',
              method: 'GET',
              data: {
                id: this.data.user.id
              },
              success:(res) => {
                if(!res.data.data) {
                  wx.switchTab({
                    url: '/pages/takingOrder/takingOrder',
                  })
                }else {
                  wx.showToast({
                    title: '该账号已被封禁',
                    icon: 'error',
                    duration: 1000
                  })
                }
              }
            })
          }else {
            wx.showToast({
              title: '邮箱或密码错误',
              icon: 'error',
              duration: 500
            })
          }
        },
        fail:() => {
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
  
  onInputMailbox: function(e) {
    this.setData({
      mailbox: e.detail.value
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
    this.setData({
      user: app.globalData.user
    })
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