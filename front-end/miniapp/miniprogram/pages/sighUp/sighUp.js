// pages/sighUp/sighUp.js
const app = getApp()
const encryption = require('../../utils/encryption')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    eye_images: ["https://i.postimg.cc/kGSdxFtj/eye-hidden.png", "https://i.postimg.cc/ryRcXcYS/eye-show.png"],
    equal_images: ["https://i.postimg.cc/xTXZMNg1/warning.jpg", "https://i.postimg.cc/PfSGmjj2/correct.jpg"],
    show: 0,
    is_equal: 1,
    user: {},
    name: "",
    mailbox: "",
    password: "",
    password2: "",
    radios: [
      { type: '用户', key: 0, checked: true },
      { type: '骑手', key: 1, checked: false },
    ],
    type: 0
  },

  switchPasswordDisplay: function() {
    this.setData({
      show: this.data.show ^ 1
    })
  },

  onInputName: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  
  onInputMailbox: function(e) {
    this.setData({
      mailbox: e.detail.value
    })
  },
  
  onInputPassword: function(e) {
    this.setData({
      password: e.detail.value,
      is_equal: e.detail.value === this.data.password2 ? 1 : 0
    })
  },
  
  onInputPassword2: function(e) {
    this.setData({
      password2: e.detail.value,
      is_equal: this.data.password === e.detail.value ? 1 : 0
    })
  },

  radioChange: function(e) {
    this.setData({
      type: e.detail.value === "用户" ? 0 : 1
    })
  },

  signUp: function () {
    let str = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
    if(!str.test(this.data.mailbox)){
      wx.showToast({
        title: '邮箱不合法',
        duration: 1000,
        icon: 'error',
      })
    }else if(this.data.password.length < 6 || this.data.password.length > 18){
      wx.showToast({
        title: '密码长应为6-18',
        duration: 1000,
        icon: 'error',
      })
    }else if(!this.data.is_equal){
      wx.showToast({
        title: '密码不一致',
        duration: 1000,
        icon: 'error',
      })
    }else {
      wx.showLoading({
        title: '注册中',
      })
      setTimeout(() => {
        if(this.data.type === 0) {
          wx.request({
            url: app.globalData.urlPort + '/api/client/register',
            method: 'POST',
            data: {
              clientName: this.data.name,
              email: this.data.mailbox,
              password: this.data.password,
              avatarUrl: "https://i.postimg.cc/W35pk90h/default.png"
            },
            success:(res) => {
              wx.hideLoading()
              if(res.data.code === 0) {
                wx.showToast({
                  title: '注册成功',
                  duration: 1000,
                  icon: 'success',
                })
                setTimeout(() => {
                  wx.redirectTo({
                    url: '/pages/login/login',
                  })
                },1000)
              }else if(res.data.code === "4444") {
                wx.showToast({
                  title: '该邮箱已注册',
                  duration: 1000,
                  icon: 'error',
                })
              }
            },
            fail:() => {
              wx.hideLoading()
              wx.showToast({
                title: '服务器繁忙',
                duration: 1000,
                icon: 'loading',
              })
            }
          })
        }else {
          wx.request({
            url: app.globalData.urlPort + '/api/courier/register',
            method: 'POST',
            data: {
              clientName: this.data.name,
              email: this.data.mailbox,
              password: this.data.password,
              avatarUrl: "https://i.postimg.cc/W35pk90h/default.png"
            },
            success:(res) => {
              wx.hideLoading()
              if(res.data.code === 0) {
                wx.showToast({
                  title: '注册成功',
                  duration: 1000,
                  icon: 'success',
                })
                setTimeout(() => {
                  wx.redirectTo({
                    url: '/pages/login/login',
                  })
                }, 1000)
              }else if(res.data.code === "4444") {
                wx.showToast({
                  title: '该邮箱已注册',
                  duration: 1000,
                  icon: 'error',
                })
              }
            },
            fail:() => {
              wx.hideLoading()
              wx.showToast({
                title: '服务器繁忙',
                duration: 1000,
                icon: 'loading',
              })
            }
          })
        }
      }, 1000)
    }
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