package service

import (
	"context"
	"fmt"
	"googlemaps.github.io/maps"
	"gopkg.in/yaml.v2"
	"io/ioutil"
	"log"
	"time"
)

var creds *Credentials

type Credentials struct {
	MapAPIKey string `yaml:"map"`
}

func init() {
	configPath := fmt.Sprintf("config/%s", "credentials.yml")

	buf, err := ioutil.ReadFile(configPath)
	if err != nil {
		fmt.Println(err)
		panic("failed to load env credentials")
	}

	credentials := &Credentials{}
	err = yaml.Unmarshal(buf, credentials)
	if err != nil {
		fmt.Println(err)
		panic("failed to map credentials to struct")
	}

	creds = credentials
}

func Duration() time.Duration {
	key := creds.MapAPIKey
	fmt.Println(key)
	c, err := maps.NewClient(maps.WithAPIKey(key))
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	r := &maps.DistanceMatrixRequest{
		Origins:      []string{"博多駅"},
		Destinations: []string{"別府駅"},
	}

	resp, err := c.DistanceMatrix(context.Background(), r)
	if err != nil {
		log.Fatalf("fatal error: %s", err)
	}

	duration := time.Second * 0
	for _, row := range resp.Rows {
		for _, me := range row.Elements {
			duration += me.Duration
		}
	}

	return duration
}
