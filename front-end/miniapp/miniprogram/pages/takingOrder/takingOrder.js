// pages/takingOrder/takingOrder.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperList: [
      {id: 1, image: 'https://i.postimg.cc/wjWKcpD9/food1.jpg'},
      {id: 2, image: 'https://i.postimg.cc/Rh1y35M5/food2.jpg'},
      {id: 3, image: 'https://i.postimg.cc/qM0WPRLk/food3.jpg'},
      {id: 4, image: 'https://i.postimg.cc/jdXBtbgy/food4.jpg'}
    ],
    typeBar: ['外卖', '快递', '闪送'],
    currentType: 0,
    showOrders: [],
    allOrders: [],
    totalOrders: 0,
    currentPage: 1,
    pageSize: 10,
    isLoading: false,
  },

  typeBarTap: function(e) {
    var cur = e.currentTarget.dataset.current;
    if (this.data.currentType !== cur){
      this.setData({
        currentType: cur
      })
    }
    this.getShowOrders()
  },

  typeToNumber(str) {
    return str === '外卖' ? 0 : (str === '快递' ? 1 : 2);
  },

  getAllOrders() {
    this.setData({
      currentPage: 1,
      isLoading: false,
    })
    // 获取订单数量
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/unaccepted',
      method: 'GET',
      success:(res) => {
        this.setData({
          totalOrders: res.data.data
        })
        if(this.data.totalOrders === 0) {
          this.setData({
            allOrders: [],
            showOrders: [],
          })
          app.globalData.ordersToTake = []
          return 
        }
        // 获取订单
        wx.request({
          url: app.globalData.urlPort + '/api/order/courier/unaccepted',
          method: 'GET',
          data: {
            current: this.data.currentPage,
            size: this.data.pageSize
          },
          success:(res) => {
            this.setData({
              allOrders: res.data.data
            })
            for (let i = 0; i < this.data.allOrders.length; i++) {
              const e = this.data.allOrders[i];
              wx.request({
                url: app.globalData.urlPort + '/api/client/get',
                method: 'GET',
                data: {
                  id: e.clientId,
                },
                success:(res) => {
                  this.setData({
                    ['allOrders[' + i + '].client'] : res.data.data
                  })
                  wx.request({
                    url: app.globalData.urlPort + '/api/deliveryAddress/get',
                    method: 'GET',
                    data: {
                      id: e.deliveryAddressId
                    },
                    success:(res) => {
                      this.setData({
                        ['allOrders[' + i + '].deliveryAddress'] : res.data.data
                      })
                      wx.request({
                        url: app.globalData.urlPort + '/api/track',
                        method: 'GET',
                        data: {
                          id: e.orderTrackingId
                        },
                        success:(res) => {
                          this.setData({
                            ['allOrders[' + i + '].orderTracking'] : res.data.data
                          })
                          app.globalData.ordersToTake = this.data.allOrders
                          this.getShowOrders()
                        }
                      })
                    }
                  })
                },
              })
            }
          },
        })
      }
    })
  },

  getShowOrders() {
    this.setData({
      showOrders: []
    })
    this.data.allOrders.forEach(e => {
      if(this.typeToNumber(e.serviceType) === this.data.currentType){
        this.setData({
          showOrders: [...this.data.showOrders, e]
        })
      }
    })
  },

  onScrollToLower() {
    if(this.data.currentPage * this.data.pageSize >= this.data.totalOrders || this.data.isLoading) return 
    this.setData({
      currentPage: this.data.currentPage + 1,
      isLoading: true,
    })
    wx.showLoading({
      title: '处理中...',
    })
    setTimeout(() => {
      wx.request({
        url: app.globalData.urlPort + '/api/order/courier/unaccepted',
        method: 'GET',
        data: {
          current: this.data.currentPage,
          size: this.data.pageSize
        },
        success:(res) => {
          this.setData({
            allOrders: this.data.allOrders.concat(res.data.data)
          })
          for (let i = (this.data.currentPage-1)*this.data.pageSize; i < this.data.allOrders.length; i++) {
            const e = this.data.allOrders[i];
            wx.request({
              url: app.globalData.urlPort + '/api/client/get',
              method: 'GET',
              data: {
                id: e.clientId,
              },
              success:(res) => {
                this.setData({
                  ['allOrders[' + i + '].client'] : res.data.data
                })
                wx.request({
                  url: app.globalData.urlPort + '/api/deliveryAddress/get',
                  method: 'GET',
                  data: {
                    id: e.deliveryAddressId
                  },
                  success:(res) => {
                    this.setData({
                      ['allOrders[' + i + '].deliveryAddress'] : res.data.data
                    })
                    wx.request({
                      url: app.globalData.urlPort + '/api/track',
                      method: 'GET',
                      data: {
                        id: e.orderTrackingId
                      },
                      success:(res) => {
                        this.setData({
                          ['allOrders[' + i + '].orderTracking'] : res.data.data
                        })
                        app.globalData.ordersToTake = this.data.allOrders
                        this.getShowOrders()
                      }
                    })
                  }
                })
              },
            })
          }
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
            isLoading: false
          })
        }
      })
    }, 500);
  },

  takeThisOrder(e) {
    wx.showLoading({
      title: '处理中...',
    })
    setTimeout(() => {
      wx.request({
        url: app.globalData.urlPort + '/api/order/status',
        method: 'PATCH',
        data: {
          id: e.currentTarget.dataset.id,
          currentStatus: '已接单',
          courierId: app.globalData.user.id
        },
        success:() => {
          this.getAllOrders()
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
        }
      })
    }, 1000);
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
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: "takingOrder"
      })
    }
    this.getAllOrders()
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