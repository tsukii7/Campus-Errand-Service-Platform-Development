// pages/modifyAddress/modifyAddress.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {}, 
    address: [],
    currentPage: 1,
    pageSize: 10,
    addressCnt: 0,
    isLoading: false,
  },

  getDeliveryAddress() {

    wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/count',
        method: 'GET',
        data: {
          clientId: app.globalData.user.id
        },
        success:(res) => {
          this.setData({
            addressCnt: res.data.data
          })
        }
      })

    wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/page',
        method: 'GET',
        data: {
          clientId: app.globalData.user.id,
          current: this.data.currentPage,
          size: this.data.pageSize,
        },
        success:(res) => {
          this.setData({
            address: res.data.data
          })
        }
      })
  },

  onScrollToLower() {
    if(this.data.isLoading) return
    if(this.data.currentPage * this.data.pageSize < this.data.addressCnt) {
      this.setData({
        'currentPage': this.data.currentPage + 1,
        'isLoading': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/deliveryAddress/page',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage,
              size: this.data.pageSize,
            },
            success:(res) => {
              this.setData({
                address: this.data.address.concat(res.data.data)
              })
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading': false
              })
            }
        })
      }, 500);
    }
  },

  addAddress: function() {
      wx.navigateTo({
        url: '/pages/addAddress/addAddress',
      })
  },
  
  delAddress: function(e) {
    this.setData({
        address: this.data.address
      })
      wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/delete',
        method: 'DELETE',
        data: {
          id: e.currentTarget.dataset.id
        },
        header: {
            "content-type":"application/x-www-form-urlencoded"
        },
        success:(res) => {
            console.log(res)
          if(res) {
            wx.showToast({
                title: '删除成功',
                icon: 'success',
                duration: 1000
              })
          }
          this.getDeliveryAddress();
        }
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
    this.getDeliveryAddress();
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