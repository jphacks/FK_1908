package main

import (
	"github.com/gin-gonic/gin"
	"github.com/jphacks/FK_1908/server/bread-api/handlers"
)

func main() {
	r := gin.Default()
	r.GET("/duration", handlers.DurationHandler())
	r.GET("/alarm", handlers.AlarmHandler())

	if err := r.Run(":8000"); err != nil {
		panic(err)
	}
}
