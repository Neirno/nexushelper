<?php

namespace app\controllers;

use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\web\Response;
use yii\filters\VerbFilter;
use app\models\LoginForm;
use app\models\ContactForm;
use app\models\DonateForm;
use app\models\Prices;

class SiteController extends Controller
{

    /**
     * {@inheritdoc}
     */
    public function actions()
    {
        return [
            'error' => [
                'class' => 'yii\web\ErrorAction',
            ],
            'captcha' => [
                'class' => 'yii\captcha\CaptchaAction',
                'fixedVerifyCode' => YII_ENV_TEST ? 'testme' : null,
            ],
        ];
    }

    /**
     * Displays homepage.
     *
     * @return string
     */
    public function actionIndex()
    {
        $model = new DonateForm();
        $prices = Prices::find()
            ->orderBy('id')
            ->all();
        if ($model->load(Yii::$app->request->post()) && $model->validate()) {
            return $this->render('login', [
               'model' => $model,
            ]);
        }
        return $this->render('index', [
            'model' => $model,
            'prices' => $prices,
        ]);
    }
}
