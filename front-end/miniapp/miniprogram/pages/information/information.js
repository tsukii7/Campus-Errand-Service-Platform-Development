// pages/information/information.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {}, 
    imgPath: "",
    name: "",
  },

  selectImg: function() {
    var that = this;
    wx.chooseMedia({
        count: 1,
        mediaType: ['image'],
        sourceType: ['album'],
        success:function(res) {
            var tempFilePath = res.tempFiles[0].tempFilePath;
            that.setData({
                imgPath: tempFilePath
            })
        }
  })
},

  onNameChange(e) {
    this.setData({
        name: e.detail.value
    })
  },

  changeInfo() {
    if(app.globalData.user.role == 0) { // yonghu
        wx.request({
            url: app.globalData.urlPort + '/api/client/update',
            method: 'PATCH',
            data: {
              id: app.globalData.user.id,
              clientName: this.data.name,
              avatarUrl: this.data.imgPath
            },
            success:(res) => {
                wx.showToast({
                    title: '修改成功',
                    icon: 'success',
                    duration: 1000
                  })
            }
          })
    } else {
        wx.request({
            url: app.globalData.urlPort + '/api/courier/update',
            method: 'PATCH',
            data: {
              id: app.globalData.user.id,
              courierName: this.data.name,
              avatarUrl: this.data.imgPath
            },
            success:(res) => {
                wx.showToast({
                    title: '修改成功',
                    icon: 'success',
                    duration: 1000
                  })
            }
          })
    }
    
    wx.navigateBack({

    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      user: app.globalData.user,
      imgPath: app.globalData.user.avatar
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