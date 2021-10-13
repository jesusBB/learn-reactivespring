def gv

pipeline {
  agent any
  parameters {
      choice(name: 'CHOICES', choices: ['1', '2', '3'], description: 'testing choices param')
  }
  //tools{
  //    maven 'Maven'     
  //}

  environment {
      NEW_VERSION = '1.3.0'
  }
  stages {
      stage("init"){
          steps{
              script {
                      gv = load "groovyScripts/script.groovy"
              }
          }
      }
      stage("build"){
          steps{
              script {
                  gv.buildApp()
              }
          }
      }
      stage("test"){
          steps{
              withMaven(
                  maven: 'Maven'
              ){
                  script {
                      gv.testApp()
                  }
              }

              //script {
                //  gv.testApp()
              //}
          }
      }
      stage("deploy"){
          steps{
              script {
     		      gv.deployApp()	
              }
          }
          post{
             always {
     	         echo 'stage deploy is finished'   
                 cleanWs(cleanWhenNotBuilt: false,
                     deleteDirs: true,
                     disableDeferredWipeout: true,
                     notFailBuild: true,
                     patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
             }
          }
      }
  }
  post{
    always {
     	echo 'stage or pipeline is finished'   
    }
}
}
