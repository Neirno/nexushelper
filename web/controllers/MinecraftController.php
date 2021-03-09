<?php

namespace app\controllers;

use Yii;
use yii\filters\AccessControl;
use yii\helpers\ArrayHelper;
use yii\web\Controller;
use yii\web\Response;
use yii\filters\VerbFilter;
use app\models\Donate;
use app\models\Prices;

class MinecraftController extends \yii\web\Controller
{
    public $layout = 'auth';

    public function actionIndex()
    {
        $request = Yii::$app->request;
        $hash = $request->get('hash');
        if ($hash != 'bf1822f170dc384c42f12a98535610ca3c5ff17af9c1aeff343224cec863c382')
            return 'умный самый?';
        $hwid = $request->get('hwid');
        $name = $request->get('name');
        $donate = $request->get('donate');
        $command = $request->get('command');
        if ($command == 'start')
        {
            if ($hwid != null && $name != null)
            {
                $auth = Donate::find()
                    ->where(['hwid' => $hwid])
                    ->all();
                if ($auth != null)
                {
                    $array = ArrayHelper::map($auth, 'nick', 'id', 'donate');
                    $nicks = '';
                    foreach ($array as $donate => $value)
                        foreach ($value as $nick => $id)
                            if ($nick == $name)
                                return 'ok' . $donate /*. '$' . $id*/;
                            $nicks .= $nick . ' - ' . $donate . ' ';
    
                    return 'no nick but hwid' . $nicks;
                }
                else
                    return 'no nick no hwid';
            }
            else 
                return 'nook';
        }
        else if($command == 'prices')
        {
            $prices = Prices::find()
                ->orderBy('id')
                ->all();
            $array = ArrayHelper::map($prices, 'donate', 'priceInGame');
            $nicks = '';
            foreach ($array as $donate => $priceInGame)
                $nicks .= $donate . '=' . $priceInGame . ' ';
            return $nicks;
        }
        // Ваще нихрена не безопасно. TODO: Переделать.
        else if($command == 'clear')
        {
            if ($hwid != null && $name != null)
            {
                $delete = Donate::find()
                ->where(['hwid' => $hwid, 'nick' => $name])
                ->one()
                ->delete();
            }
            else 
                return 'nook';
        }
        else 
            return 'kavo?';
        
    }
}