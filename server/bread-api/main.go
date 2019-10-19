package main

import (
	"fmt"
	"io/ioutil"
	"log"

	"github.com/kr/pretty"
	"golang.org/x/net/context"
	"googlemaps.github.io/maps"
	"gopkg.in/yaml.v2"
)

type Credentials struct {
	MapAPIKey string `yaml:"map"`
}

func newCred() *Credentials {
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

	return credentials
}

func main() {

}
