package handlers

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/jphacks/FK_1908/server/bread-api/service"
	"net/http"
	"time"
)

func DurationHandler() gin.HandlerFunc {
	return func(c *gin.Context) {
		duration := service.Duration()
		c.JSON(http.StatusOK, duration)
	}
}

func AlarmHandler() gin.HandlerFunc {
	return func(c *gin.Context) {
		now := time.Now()
		st := fmt.Sprintf("%s 00:00:00 %s", now.Format("2006-01-02"), now.Format("-0700"))
		day, _ := time.Parse("2006-01-02 15:04:05 -0700", st)
		defaultAlerm := day.Add(time.Hour * (24 + 8)) // tomorrow 8:00 a.m.

		duration := service.Duration()
		alerm := defaultAlerm.Add(-duration)

		c.JSON(http.StatusOK, alerm)
	}
}
