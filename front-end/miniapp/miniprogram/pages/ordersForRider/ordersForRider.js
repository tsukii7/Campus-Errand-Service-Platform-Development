const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    navbar: ['全部','待取货','配送中','已送达','已完成'],
    currentTab: 0,
    navScrollLeft: 0,
    iconPath: ['https://i.postimg.cc/J7gGxfcc/takeAway.png', 'https://i.postimg.cc/KYnPhZQ2/express.png', 'https://i.postimg.cc/yx6XTwmz/delivery.png'],
    ordersStatusAll: [],
    ordersStatus0: [],
    ordersStatus1: [],
    ordersStatus2: [],
    ordersStatus3: [],
    currentPage: [1, 1, 1, 1, 1],
    pageSize: [10, 10, 10, 10, 10],
    totalOrders: [0, 0, 0, 0, 0],
    isLoading: [false, false, false, false, false],
  },

  getOrders() {
    this.setData({
      currentPage: [1, 1, 1, 1, 1],
      isLoading: [false, false, false, false, false],
    })

    // 获取订单数量
    // 全部
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/courier/all',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[0]': res.data.data
        })
      }
    })

    // 待取货
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/courier/untransitted',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[1]': res.data.data
        })
      }
    })

    // 配送中
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/courier/transitting',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[2]': res.data.data
        })
      }
    })

    // 已送达
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/courier/arrived',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[3]': res.data.data
        })
      }
    })

    // 已完成
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/courier/confirmed',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[4]': res.data.data
        })
      }
    })

    // 获取订单
    // 全部
    wx.request({
      url: app.globalData.urlPort + '/api/order/courier/all',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id,
        current: this.data.currentPage[0],
        size: this.data.pageSize[0],
      },
      success:(res) => {
        this.setData({
          ordersStatusAll: res.data.data
        })
        app.globalData.orders = this.data.ordersStatusAll
      }
    })
    
    // 待取货
    wx.request({
      url: app.globalData.urlPort + '/api/order/courier/untransitted',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id,
        current: this.data.currentPage[1],
        size: this.data.pageSize[1],
      },
      success:(res) => {
        this.setData({
          ordersStatus0: res.data.data
        })
      }
    })

    // 配送中
    wx.request({
      url: app.globalData.urlPort + '/api/order/courier/transitting',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id,
        current: this.data.currentPage[2],
        size: this.data.pageSize[2],
      },
      success:(res) => {
        this.setData({
          ordersStatus1: res.data.data
        })
      }
    })

    // 已送达
    wx.request({
      url: app.globalData.urlPort + '/api/order/courier/arrived',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id,
        current: this.data.currentPage[3],
        size: this.data.pageSize[3],
      },
      success:(res) => {
        this.setData({
          ordersStatus2: res.data.data
        })
      }
    })

    // 已完成
    wx.request({
      url: app.globalData.urlPort + '/api/order/courier/confirmed',
      method: 'GET',
      data: {
        courierId: app.globalData.user.id,
        current: this.data.currentPage[4],
        size: this.data.pageSize[4],
      },
      success:(res) => {
        this.setData({
          ordersStatus3: res.data.data
        })
      }
    })
  },

  onScrollToLowerAll() {
    if(this.data.isLoading[0]) return
    if(this.data.currentPage[0] * this.data.pageSize[0] < this.data.totalOrders[0]) {
      this.setData({
        'currentPage[0]': this.data.currentPage[0] + 1,
        'isLoading[0]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/courier/all',
            method: 'GET',
            data: {
              courierId: app.globalData.user.id,
              current: this.data.currentPage[0],
              size: this.data.pageSize[0],
            },
            success:(res) => {
              this.setData({
                ordersStatusAll: this.data.ordersStatusAll.concat(res.data.data)
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
                'isLoading[0]': false
              })
            }
        })
      }, 500);
      app.globalData.orders = this.data.ordersStatusAll
    }
  },

  onScrollToLower0() {
    if(this.data.isLoading[1]) return
    if(this.data.currentPage[1] * this.data.pageSize[1] < this.data.totalOrders[1]) {
      this.setData({
        'currentPage[1]': this.data.currentPage[1] + 1,
        'isLoading[1]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/courier/untransitted',
            method: 'GET',
            data: {
              courierId: app.globalData.user.id,
              current: this.data.currentPage[1],
              size: this.data.pageSize[1],
            },
            success:(res) => {
              this.setData({
                ordersStatus0: this.data.ordersStatus0.concat(res.data.data)
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
                'isLoading[1]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower1() {
    if(this.data.isLoading[2]) return
    if(this.data.currentPage[2] * this.data.pageSize[2] < this.data.totalOrders[2]) {
      this.setData({
        'currentPage[2]': this.data.currentPage[2] + 1,
        'isLoading[2]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/courier/transitting',
            method: 'GET',
            data: {
              courierId: app.globalData.user.id,
              current: this.data.currentPage[2],
              size: this.data.pageSize[2],
            },
            success:(res) => {
              this.setData({
                ordersStatus1: this.data.ordersStatus1.concat(res.data.data)
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
                'isLoading[2]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower2() {
    if(this.data.isLoading[3]) return
    if(this.data.currentPage[3] * this.data.pageSize[3] < this.data.totalOrders[3]) {
      this.setData({
        'currentPage[3]': this.data.currentPage[3] + 1,
        'isLoading[3]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/courier/arrived',
            method: 'GET',
            data: {
              courierId: app.globalData.user.id,
              current: this.data.currentPage[3],
              size: this.data.pageSize[3],
            },
            success:(res) => {
              this.setData({
                ordersStatus2: this.data.ordersStatus2.concat(res.data.data)
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
                'isLoading[3]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower3() {
    if(this.data.isLoading[4]) return
    if(this.data.currentPage[4] * this.data.pageSize[4] < this.data.totalOrders[4]) {
      this.setData({
        'currentPage[4]': this.data.currentPage[4] + 1,
        'isLoading[4]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/courier/confirmed',
            method: 'GET',
            data: {
              courierId: app.globalData.user.id,
              current: this.data.currentPage[4],
              size: this.data.pageSize[4],
            },
            success:(res) => {
              this.setData({
                ordersStatus3: this.data.ordersStatus3.concat(res.data.data)
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
                'isLoading[4]': false
              })
            }
        })
      }, 500);
    }
  },

  navbarTap: function(e){
    var cur = e.currentTarget.dataset.current; 
    var singleNavWidth = this.data.windowWidth / 5;
    this.setData({
        navScrollLeft: (cur - 2) * singleNavWidth
    })      
    if (this.data.currentTab == cur) {
        return false;
    } else {
        this.setData({
            currentTab: cur
        })
    }
  },

  switchNav(e) {
    var cur = e.detail.current;
    var singleNavWidth = this.data.windowWidth / 5;
    this.setData({
      currentTab: cur,
      navScrollLeft: (cur - 2) * singleNavWidth
    });
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
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: "ordersForRider"
      })
    }
    this.getOrders()
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
    wx.stopPullDownRefresh()
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